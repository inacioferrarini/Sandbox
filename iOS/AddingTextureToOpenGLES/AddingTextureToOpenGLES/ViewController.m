//
//  ViewController.m
//  AddingTextureToOpenGLES
//
//  Created by José Inácio Athayde Ferrarini on 25/11/12.
//  Copyright (c) 2012 José Inácio Athayde Ferrarini. All rights reserved.
//

#import "ViewController.h"

#define BUFFER_OFFSET(i) ((char *)NULL + (i))

// Uniform index.
enum
{
    UNIFORM_MODELVIEWPROJECTION_MATRIX,
    UNIFORM_NORMAL_MATRIX,
    NUM_UNIFORMS
};
GLint uniforms[NUM_UNIFORMS];

// Attribute index.
enum
{
    ATTRIB_VERTEX,
    ATTRIB_NORMAL,
    NUM_ATTRIBUTES
};

GLfloat gCubeVertexData[] =
{
    // Data layout for each line below is:
    // positionX, positionY, positionZ,     normalX, normalY, normalZ, texCoordS, texCoordT
    0.5f, -0.5f, -0.5f,        1.0f, 0.0f, 0.0f,    0,1,
    0.5f, 0.5f, -0.5f,         1.0f, 0.0f, 0.0f,    1,1,
    0.5f, -0.5f, 0.5f,         1.0f, 0.0f, 0.0f,    0,0,
    0.5f, -0.5f, 0.5f,         1.0f, 0.0f, 0.0f,    0,0,
    0.5f, 0.5f, 0.5f,          1.0f, 0.0f, 0.0f,    1,0,
    0.5f, 0.5f, -0.5f,         1.0f, 0.0f, 0.0f,    1,1,
    
    0.5f, 0.5f, -0.5f,         0.0f, 1.0f, 0.0f,    1,1,
    -0.5f, 0.5f, -0.5f,        0.0f, 1.0f, 0.0f,    0,1,
    0.5f, 0.5f, 0.5f,          0.0f, 1.0f, 0.0f,    1,0,
    0.5f, 0.5f, 0.5f,          0.0f, 1.0f, 0.0f,    1,0,
    -0.5f, 0.5f, -0.5f,        0.0f, 1.0f, 0.0f,    0,1,
    -0.5f, 0.5f, 0.5f,         0.0f, 1.0f, 0.0f,    0,0,
    
    -0.5f, 0.5f, -0.5f,        -1.0f, 0.0f, 0.0f,   1,1,
    -0.5f, -0.5f, -0.5f,       -1.0f, 0.0f, 0.0f,   0,1,
    -0.5f, 0.5f, 0.5f,         -1.0f, 0.0f, 0.0f,   1,0,
    -0.5f, 0.5f, 0.5f,         -1.0f, 0.0f, 0.0f,   1,0,
    -0.5f, -0.5f, -0.5f,       -1.0f, 0.0f, 0.0f,   0,1,
    -0.5f, -0.5f, 0.5f,        -1.0f, 0.0f, 0.0f,   0,0,
    
    -0.5f, -0.5f, -0.5f,       0.0f, -1.0f, 0.0f,   0,1,
    0.5f, -0.5f, -0.5f,        0.0f, -1.0f, 0.0f,   1,1,
    -0.5f, -0.5f, 0.5f,        0.0f, -1.0f, 0.0f,   0,0,
    -0.5f, -0.5f, 0.5f,        0.0f, -1.0f, 0.0f,   0,0,
    0.5f, -0.5f, -0.5f,        0.0f, -1.0f, 0.0f,   1,1,
    0.5f, -0.5f, 0.5f,         0.0f, -1.0f, 0.0f,   1,0,
    
    0.5f, 0.5f, 0.5f,          0.0f, 0.0f, 1.0f,    1,0,
    -0.5f, 0.5f, 0.5f,         0.0f, 0.0f, 1.0f,    0,0,
    0.5f, -0.5f, 0.5f,         0.0f, 0.0f, 1.0f,    1,1,
    0.5f, -0.5f, 0.5f,         0.0f, 0.0f, 1.0f,    1,1,
    -0.5f, 0.5f, 0.5f,         0.0f, 0.0f, 1.0f,    0,0,
    -0.5f, -0.5f, 0.5f,        0.0f, 0.0f, 1.0f,    0,1,
    
    0.5f, -0.5f, -0.5f,        0.0f, 0.0f, -1.0f,   1,1,
    -0.5f, -0.5f, -0.5f,       0.0f, 0.0f, -1.0f,   0,1,
    0.5f, 0.5f, -0.5f,         0.0f, 0.0f, -1.0f,   1,0,
    0.5f, 0.5f, -0.5f,         0.0f, 0.0f, -1.0f,   1,0,
    -0.5f, -0.5f, -0.5f,       0.0f, 0.0f, -1.0f,   0,1,
    -0.5f, 0.5f, -0.5f,        0.0f, 0.0f, -1.0f,   0,0
};

@interface ViewController () {
    GLuint _program;
    
    GLKMatrix4 _modelViewProjectionMatrix;
    GLKMatrix3 _normalMatrix;
    float _rotation;
    
    GLuint _vertexArray;
    GLuint _vertexBuffer;
    
    
    
    GLKTextureInfo *_texture; //tex
}
@property (strong, nonatomic) EAGLContext *context;
@property (strong, nonatomic) GLKBaseEffect *effect;

- (void)setupGL;
- (void)tearDownGL;

- (BOOL)loadShaders;
- (BOOL)compileShader:(GLuint *)shader type:(GLenum)type file:(NSString *)file;
- (BOOL)linkProgram:(GLuint)prog;
- (BOOL)validateProgram:(GLuint)prog;



- (void)loadTextures; //tex
- (void)logError:(NSError *) error; //tex
@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.context = [[EAGLContext alloc] initWithAPI:kEAGLRenderingAPIOpenGLES2];

    if (!self.context) {
        NSLog(@"Failed to create ES context");
    }
    
    GLKView *view = (GLKView *)self.view;
    view.context = self.context;
    view.drawableDepthFormat = GLKViewDrawableDepthFormat24;
    
    [self setupGL];
}

- (void)dealloc
{    
    [self tearDownGL];
    
    if ([EAGLContext currentContext] == self.context) {
        [EAGLContext setCurrentContext:nil];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];

    if ([self isViewLoaded] && ([[self view] window] == nil)) {
        self.view = nil;
        
        [self tearDownGL];
        
        if ([EAGLContext currentContext] == self.context) {
            [EAGLContext setCurrentContext:nil];
        }
        self.context = nil;
    }

    // Dispose of any resources that can be recreated.
}

- (void)setupGL
{
    [EAGLContext setCurrentContext:self.context];
    
    [self loadShaders];
    
    [self loadTextures]; //tex
    
    self.effect = [[GLKBaseEffect alloc] init];
    self.effect.light0.enabled = GL_TRUE;
    self.effect.light0.diffuseColor = GLKVector4Make(1.0f, 1.0f, 1.0f, 1.0f); // tex
    self.effect.texture2d0.enabled = GL_TRUE; // tex
    self.effect.texture2d0.envMode = GLKTextureEnvModeModulate; // tex
    self.effect.texture2d0.target = GLKTextureTarget2D; // tex
    self.effect.texture2d0.name = _texture.name; // tex
    
    glEnable(GL_DEPTH_TEST);
    
    glGenVertexArraysOES(1, &_vertexArray);
    glBindVertexArrayOES(_vertexArray);
    
    glGenBuffers(1, &_vertexBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, _vertexBuffer);
    glBufferData(GL_ARRAY_BUFFER, sizeof(gCubeVertexData), gCubeVertexData, GL_STATIC_DRAW);
    
    glEnableVertexAttribArray(GLKVertexAttribPosition);
    glVertexAttribPointer(GLKVertexAttribPosition, 3, GL_FLOAT, GL_FALSE, 32, BUFFER_OFFSET(0)); // tex
    glEnableVertexAttribArray(GLKVertexAttribNormal);   // tex
    glVertexAttribPointer(GLKVertexAttribNormal, 3, GL_FLOAT, GL_FALSE, 32, BUFFER_OFFSET(12)); // tex
    glEnableVertexAttribArray(GLKVertexAttribTexCoord0); // tex
    glVertexAttribPointer(GLKVertexAttribTexCoord0, 2, GL_FLOAT, GL_FALSE, 32, BUFFER_OFFSET(24)); // tex
    
    glBindVertexArrayOES(0);
}

- (void)tearDownGL
{
    [EAGLContext setCurrentContext:self.context];
    
    glDeleteBuffers(1, &_vertexBuffer);
    glDeleteVertexArraysOES(1, &_vertexArray);
    
    self.effect = nil;
    
    if (_program) {
        glDeleteProgram(_program);
        _program = 0;
    }
}

#pragma mark - GLKView and GLKViewController delegate methods

- (void)update
{
    float aspect = fabsf(self.view.bounds.size.width / self.view.bounds.size.height);
    GLKMatrix4 projectionMatrix = GLKMatrix4MakePerspective(GLKMathDegreesToRadians(65.0f), aspect, 0.1f, 100.0f);
    
    self.effect.transform.projectionMatrix = projectionMatrix;
    
    GLKMatrix4 baseModelViewMatrix = GLKMatrix4MakeTranslation(0.0f, 0.0f, -4.0f);
    baseModelViewMatrix = GLKMatrix4Rotate(baseModelViewMatrix, _rotation, 0.0f, 1.0f, 0.0f);
    
    // Compute the model view matrix for the object rendered with GLKit
    GLKMatrix4 modelViewMatrix = GLKMatrix4MakeTranslation(0.0f, 0.0f, -1.5f);
    modelViewMatrix = GLKMatrix4Rotate(modelViewMatrix, _rotation, 1.0f, 1.0f, 1.0f);
    modelViewMatrix = GLKMatrix4Multiply(baseModelViewMatrix, modelViewMatrix);
    
    self.effect.transform.modelviewMatrix = modelViewMatrix;
    
    // Compute the model view matrix for the object rendered with ES2
    modelViewMatrix = GLKMatrix4MakeTranslation(0.0f, 0.0f, 1.5f);
    modelViewMatrix = GLKMatrix4Rotate(modelViewMatrix, _rotation, 1.0f, 1.0f, 1.0f);
    modelViewMatrix = GLKMatrix4Multiply(baseModelViewMatrix, modelViewMatrix);
    
    _normalMatrix = GLKMatrix3InvertAndTranspose(GLKMatrix4GetMatrix3(modelViewMatrix), NULL);
    
    _modelViewProjectionMatrix = GLKMatrix4Multiply(projectionMatrix, modelViewMatrix);
    
    _rotation += self.timeSinceLastUpdate * 0.5f;
}

- (void)glkView:(GLKView *)view drawInRect:(CGRect)rect
{
    glClearColor(0.65f, 0.65f, 0.65f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glBindVertexArrayOES(_vertexArray);
    
    // Render the object with GLKit
    [self.effect prepareToDraw];
    
    glDrawArrays(GL_TRIANGLES, 0, 36);
    
    // Render the object again with ES2
    glUseProgram(_program);
    
    glUniformMatrix4fv(uniforms[UNIFORM_MODELVIEWPROJECTION_MATRIX], 1, 0, _modelViewProjectionMatrix.m);
    glUniformMatrix3fv(uniforms[UNIFORM_NORMAL_MATRIX], 1, 0, _normalMatrix.m);
    
    glDrawArrays(GL_TRIANGLES, 0, 36);
}

#pragma mark -  OpenGL ES 2 shader compilation

- (BOOL)loadShaders
{
    GLuint vertShader, fragShader;
    NSString *vertShaderPathname, *fragShaderPathname;
    
    // Create shader program.
    _program = glCreateProgram();
    
    // Create and compile vertex shader.
    vertShaderPathname = [[NSBundle mainBundle] pathForResource:@"Shader" ofType:@"vsh"];
    if (![self compileShader:&vertShader type:GL_VERTEX_SHADER file:vertShaderPathname]) {
        NSLog(@"Failed to compile vertex shader");
        return NO;
    }
    
    // Create and compile fragment shader.
    fragShaderPathname = [[NSBundle mainBundle] pathForResource:@"Shader" ofType:@"fsh"];
    if (![self compileShader:&fragShader type:GL_FRAGMENT_SHADER file:fragShaderPathname]) {
        NSLog(@"Failed to compile fragment shader");
        return NO;
    }
    
    // Attach vertex shader to program.
    glAttachShader(_program, vertShader);
    
    // Attach fragment shader to program.
    glAttachShader(_program, fragShader);
    
    // Bind attribute locations.
    // This needs to be done prior to linking.
    glBindAttribLocation(_program, GLKVertexAttribPosition, "position");
    glBindAttribLocation(_program, GLKVertexAttribNormal, "normal");
    
    // Link program.
    if (![self linkProgram:_program]) {
        NSLog(@"Failed to link program: %d", _program);
        
        if (vertShader) {
            glDeleteShader(vertShader);
            vertShader = 0;
        }
        if (fragShader) {
            glDeleteShader(fragShader);
            fragShader = 0;
        }
        if (_program) {
            glDeleteProgram(_program);
            _program = 0;
        }
        
        return NO;
    }
    
    // Get uniform locations.
    uniforms[UNIFORM_MODELVIEWPROJECTION_MATRIX] = glGetUniformLocation(_program, "modelViewProjectionMatrix");
    uniforms[UNIFORM_NORMAL_MATRIX] = glGetUniformLocation(_program, "normalMatrix");
    
    // Release vertex and fragment shaders.
    if (vertShader) {
        glDetachShader(_program, vertShader);
        glDeleteShader(vertShader);
    }
    if (fragShader) {
        glDetachShader(_program, fragShader);
        glDeleteShader(fragShader);
    }
    
    return YES;
}

- (BOOL)compileShader:(GLuint *)shader type:(GLenum)type file:(NSString *)file
{
    GLint status;
    const GLchar *source;
    
    source = (GLchar *)[[NSString stringWithContentsOfFile:file encoding:NSUTF8StringEncoding error:nil] UTF8String];
    if (!source) {
        NSLog(@"Failed to load vertex shader");
        return NO;
    }
    
    *shader = glCreateShader(type);
    glShaderSource(*shader, 1, &source, NULL);
    glCompileShader(*shader);
    
#if defined(DEBUG)
    GLint logLength;
    glGetShaderiv(*shader, GL_INFO_LOG_LENGTH, &logLength);
    if (logLength > 0) {
        GLchar *log = (GLchar *)malloc(logLength);
        glGetShaderInfoLog(*shader, logLength, &logLength, log);
        NSLog(@"Shader compile log:\n%s", log);
        free(log);
    }
#endif
    
    glGetShaderiv(*shader, GL_COMPILE_STATUS, &status);
    if (status == 0) {
        glDeleteShader(*shader);
        return NO;
    }
    
    return YES;
}

- (BOOL)linkProgram:(GLuint)prog
{
    GLint status;
    glLinkProgram(prog);
    
#if defined(DEBUG)
    GLint logLength;
    glGetProgramiv(prog, GL_INFO_LOG_LENGTH, &logLength);
    if (logLength > 0) {
        GLchar *log = (GLchar *)malloc(logLength);
        glGetProgramInfoLog(prog, logLength, &logLength, log);
        NSLog(@"Program link log:\n%s", log);
        free(log);
    }
#endif
    
    glGetProgramiv(prog, GL_LINK_STATUS, &status);
    if (status == 0) {
        return NO;
    }
    
    return YES;
}

- (BOOL)validateProgram:(GLuint)prog
{
    GLint logLength, status;
    
    glValidateProgram(prog);
    glGetProgramiv(prog, GL_INFO_LOG_LENGTH, &logLength);
    if (logLength > 0) {
        GLchar *log = (GLchar *)malloc(logLength);
        glGetProgramInfoLog(prog, logLength, &logLength, log);
        NSLog(@"Program validate log:\n%s", log);
        free(log);
    }
    
    glGetProgramiv(prog, GL_VALIDATE_STATUS, &status);
    if (status == 0) {
        return NO;
    }
    
    return YES;
}


#pragma mark -  Texture Loading

- (void)loadTextures
{
    NSError *error = nil;   // stores the error message if we mess up
    NSDictionary *options = [NSDictionary dictionaryWithObject:[NSNumber numberWithBool:YES]
                                                        forKey:GLKTextureLoaderGenerateMipmaps];
    
    NSString *bundlepath = [[NSBundle mainBundle] pathForResource:@"checkers" ofType:@"png"];
    
    _texture = [GLKTextureLoader textureWithContentsOfFile:bundlepath options:options error:&error];
    
    [self logError:error];
}

- (void)logError:(NSError *) error
{
    if (error)
    {
        NSString * domain = [error domain];
        NSLog(@"Error loading texture: %@.  Domain: %@", [error localizedDescription],domain);
        NSDictionary * userInfo = [error userInfo];
        if (domain == GLKTextureLoaderErrorDomain)
        {
            if (nil != [userInfo objectForKey:GLKTextureLoaderErrorKey])
                NSLog(@"%@", [userInfo objectForKey:GLKTextureLoaderErrorKey]);
            if (nil != [userInfo objectForKey:GLKTextureLoaderGLErrorKey])
                NSLog(@"%@", [userInfo objectForKey:GLKTextureLoaderGLErrorKey]);
        }
    }
}

@end

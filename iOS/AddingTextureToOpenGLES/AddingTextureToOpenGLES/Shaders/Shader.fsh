//
//  Shader.fsh
//  AddingTextureToOpenGLES
//
//  Created by José Inácio Athayde Ferrarini on 25/11/12.
//  Copyright (c) 2012 José Inácio Athayde Ferrarini. All rights reserved.
//

varying lowp vec4 colorVarying;

void main()
{
    gl_FragColor = colorVarying;
}

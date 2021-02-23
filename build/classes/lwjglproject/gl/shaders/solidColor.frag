#version 400
    
uniform vec4 solidColor;

layout(location = 0) out vec4 diffuseColor;

void main(void)
{
    diffuseColor = vec4 (solidColor);
}
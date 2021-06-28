#version 400

layout(location = 0) out vec4 diffuseColor;

uniform vec4 solidColor;

void main(void)
{
    diffuseColor = vec4 (solidColor);
}
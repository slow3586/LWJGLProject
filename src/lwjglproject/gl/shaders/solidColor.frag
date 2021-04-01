#version 400

layout(location = 0) out vec4 diffuseColor;

void main(void)
{
    diffuseColor = vec4 (solidColor);
}
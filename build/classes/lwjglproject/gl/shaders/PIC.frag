#version 400
    
in vec4 vertexColor_v;

layout(location = 0) out vec4 diffuseColor;

void main(void)
{
    diffuseColor = vec4 (vertexColor_v.x, vertexColor_v.y, vertexColor_v.z, vertexColor_v.w);
}
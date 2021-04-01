#version 400

layout (location = 0) in vec3 position_in;
layout (location = 1) in vec4 vertexColor_in;

uniform mat4 cameraEntityMat;

out vec4 vertexColor_v;

void main(void)
{
    vertexColor_v = vertexColor_in;
    gl_Position =  cameraEntityMat * vec4(position_in.x, position_in.y, position_in.z, 1.0);
}

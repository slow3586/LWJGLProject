#version 400

layout (location = 0) in vec3 position_in;
layout (location = 2) in vec2 uv_in;

uniform mat4 cameraEntityMat;

out vec2 uv_v;

void main(void)
{
    uv_v = uv_in;
    gl_Position =  cameraEntityMat * vec4(position_in.x, position_in.y, position_in.z, 1.0);
}

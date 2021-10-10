package lwjglproject.logic;

import lwjglproject.gl.vertexarrays.VertexArrayPI;

public class GameWorld {
    int size = 8;
    int mapheight[][];

    public GameWorld() {
        mapheight = new int[64][64];
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
               mapheight[x][y] = 0;
            }
        }
    }
}

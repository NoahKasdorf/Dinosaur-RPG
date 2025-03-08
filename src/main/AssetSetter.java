package src.main;

import src.objects.OBJ_Scroll;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new OBJ_Scroll();
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;
    }

}

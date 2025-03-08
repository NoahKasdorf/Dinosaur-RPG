package src.objects;

import javax.imageio.ImageIO;

public class OBJ_Scroll extends SuperObject {

    public OBJ_Scroll() {
        name = "Scroll";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/GoldenImage.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}

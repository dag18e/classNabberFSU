import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.List;

public class classNabber
{
    private static List<String> userClasses;

    public static void main(String[] args)
    {
        GUI frame = new GUI();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 800, 600 );
        frame.setVisible( true );
        frame.setResizable( false );
        
        //attempt to open save file, otherwise create one
        try {
            File save = new File("save.txt");

            if(save.createNewFile()) {
                frame.setStatus("Save file created in current directory");
            }
            else {
                frame.setStatus("Loading save file");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        //do stuff



    }
}
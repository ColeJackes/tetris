import java.awt.*;

public class ProgramWindow  extends  Frame
{
	private TetrisPanel panel = new TetrisPanel() ;

	public ProgramWindow( )
	{
		setTitle ("Tetris");
		setSize	(800, 600);
		setLocation (100, 100);
		setResizable( true );
		add(panel);
		setVisible(true);
		this.addKeyListener(panel);
	}

}

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TetrisPanel extends Panel implements KeyListener
{

	public TetrisPanel()
	{
		addKeyListener(this);
		Gametimer();
	}

	//Creating all seven forms of tetris pieces, with four orientations for each.

	int[][][] piece1 = new int[][][]
	{
		{
			{0, 0, 1, 0},
			{0, 0, 1, 0},
			{0, 0, 1, 0},
			{0, 0, 1, 0}
		},

		{
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 1, 0},
			{0, 0, 1, 0},
			{0, 0, 1, 0},
			{0, 0, 1, 0}
		},

		{
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},
	};


	int[][][] piece2 = new int[][][]
	{
		{
			{0, 2, 2, 0},
			{0, 0, 2, 0},
			{0, 0, 2, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 0, 2},
			{0, 2, 2, 2},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 2, 0},
			{0, 0, 2, 0},
			{0, 0, 2, 2},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 0, 0},
			{0, 2, 2, 2},
			{0, 2, 0, 0},
			{0, 0, 0, 0}
		},
	};

	int[][][] piece3 = new int[][][]
	{
		{
			{0, 0, 3, 3},
			{0, 0, 3, 0},
			{0, 0, 3, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 0, 0},
			{0, 3, 3, 3},
			{0, 0, 0, 3},
			{0, 0, 0, 0}
		},

		{
			{0, 0, 3, 0},
			{0, 0, 3, 0},
			{0, 3, 3, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 3, 0, 0},
			{0, 3, 3, 3},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},
	};

	int[][][] piece4 = new int[][][]
	{
		{
			{4, 4, 4, 0},
			{0, 4, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 4, 0, 0},
			{4, 4, 0, 0},
			{0, 4, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 4, 0, 0},
			{4, 4, 4, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 4, 0, 0},
			{0, 4, 4, 0},
			{0, 4, 0, 0},
			{0, 0, 0, 0}
		},
	};

	int[][][] piece5 = new int[][][]
	{
		{
			{5, 5, 0, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{5, 5, 0, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{5, 5, 0, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{5, 5, 0, 0},
			{5, 5, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},
	};

	int[][][] piece6 = new int[][][]
	{
		{
			{0, 6, 6, 0},
			{6, 6, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{6, 0, 0, 0},
			{6, 6, 0, 0},
			{0, 6, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 6, 6, 0},
			{6, 6, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{6, 0, 0, 0},
			{6, 6, 0, 0},
			{0, 6, 0, 0},
			{0, 0, 0, 0}
		},
	};

	int[][][] piece7 = new int[][][]
	{
		{
			{7, 7, 0, 0},
			{0, 7, 7, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 7, 0, 0},
			{7, 7, 0, 0},
			{7, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{7, 7, 0, 0},
			{0, 7, 7, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},

		{
			{0, 7, 0, 0},
			{7, 7, 0, 0},
			{7, 0, 0, 0},
			{0, 0, 0, 0}
		}
	};

	//Creating timer to schedule the dropping of the pieces.

	Timer timer;

	//Boolean to cause a new piece to fall when one has stopped moving.

	boolean done = false;

	//Generating random number to make random first piece.

	int[][][] p = new int[10][10][4];

	public Piece randomPiece()
	{
		int piece = (int)(Math.random()*6 + 1);

		if (piece == 1)
			p = piece1;
		else if (piece == 2)
			p = piece2;
		else if (piece == 3)
			p = piece3;
		else if (piece == 4)
			p = piece4;
		else if (piece == 5)
			p = piece5;
		else if (piece == 6)
			p = piece6;
		else if (piece == 7)
			p = piece7;


		Piece p1 = new Piece(p, 0, 8, 0, piece);

		return p1;

	}

	//Creating board and first piece.

	Piece p1 = randomPiece();

	// Variable to end program if piece can't move immediately after being added to board.

	public int ctr = 0;

	public void Gametimer()
	{
		timer = new Timer();

		p1.createBoardBuffer();

		timer.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				if (done)
				{
					done = false;

					if (ctr == 0)
					{
						System.out.println("GAME OVER");
						System.exit(0);
					}

					ctr = 0;

					//Creating a new random piece.

					p1 = randomPiece();

					repaint();
				}

				//Falling down, if possible.

				if (p1.checkDown(board, p1))
				{
					System.out.println(p1.orientation);
					p1.move1(0, 1);
					ctr ++;
					repaint();
				}
				else
				{
					//Add the piece to the board semi-permanently.
					done = true;
					p1.addToBoard(p1);

					//Removes line(s) if applicable.
					p1.removeLine(board);

				}
			}
		}
		, 500, 500);
	}

	int w = 10;		//Number of columns in the board.
	int h = 20;		//Number of rows in the board.
	int d = 25;    //Length/width of each square in board.

	public int[][] board = new int[w+10][h+3];

	//Creating class for the pieces.

	public class Piece
	{
		public int[][][] tetromino;
		public int orientation;
		public int topR;
		public int leftC;
		public int piece;

		//Class constructor.

		public Piece (int[][][] t, int o, int lC, int tR, int pi)
		{
			tetromino = t;
			orientation = o;
			topR = tR;
			leftC = lC;
			piece = pi;
		}

		public void paint (Graphics g)
		{
			if (tetromino == piece1)
				g.setColor(Color.RED);
			else if (tetromino == piece2)
				g.setColor(Color.BLUE);
			else if (tetromino == piece3)
				g.setColor(Color.GREEN);
			else if (tetromino == piece4)
				g.setColor(Color.ORANGE);
			else if (tetromino == piece5)
				g.setColor(Color.YELLOW);
			else if (tetromino == piece6)
				g.setColor(Color.CYAN);
			else if (tetromino == piece7)
				g.setColor(Color.MAGENTA);

			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (tetromino[orientation][j][i] > 0)
					{
						g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
					}
				}
			}
		}

		//Checks if piece can be successfully moved or rotated.

		public boolean checkDown (int[][] b, Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (tetromino[orientation][j][i] > 0)
					{
						if(board[p1.leftC + i][p1.topR + j + 1] > 0)
						{
							return false;
						}
					}
				}
			}
			return true;
		}

		public boolean checkRight (int[][] b, Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (tetromino[orientation][j][i] > 0)
					{
						if(board[p1.leftC + i + 1][p1.topR + j] > 0)
						{
							return false;
						}
					}
				}
			}
			return true;
		}

		public boolean checkLeft (int[][] b, Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (tetromino[orientation][j][i] > 0)
					{
						if(board[p1.leftC + i - 1][p1.topR + j] > 0)
						{
							return false;
						}
					}
				}
			}
			return true;
		}

		public boolean checkUpR (int[][] b, Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (orientation < 3)
					{
						if (tetromino[orientation + 1][j][i] > 0)
						{
							if(board[p1.leftC + i][p1.topR + j] > 0)
							{
								return false;
							}
						}
					}
					else
					{
						if (tetromino[0][j][i] > 0)
						{
							if(board[p1.leftC + i][p1.topR + j] > 0)
							{
								return false;
							}
						}
					}

				}
			}
			return true;
		}

		public boolean checkDownR (int[][] b, Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (orientation > 0)
					{
						if (tetromino[orientation - 1][j][i] > 0)
						{
							if(board[p1.leftC + i][p1.topR + j] > 0)
							{
								return false;
							}
						}
					}
					else
					{
						if (tetromino[3][j][i] > 0)
						{
							if(board[p1.leftC + i][p1.topR + j] > 0)
							{
								return false;
							}
						}
					}
				}
			}
			return true;
		}

		//Adding data regarding location of the piece to the board.

		public void addToBoard (Piece p)
		{
			for (int i = 0; i < 4; i ++)
			{
				for (int j = 0; j < 4; j ++)
				{
					if (tetromino[orientation][j][i] > 0)
					{
						board[leftC + i][topR + j] = p1.piece;
					}
				}
			}
		}

		public void createBoardBuffer ()
		{
			//Filling borders of the board so that pieces can't move off.

			for (int i = 0; i < h; i ++)
			{
				board[w+5][i] = 1;
				board[4][i] = 1;
			}

			for (int i = 5; i < w + 5; i ++)
			{
				board[i][h] = 1;
			}
		}

		//Moves piece right, left or down.

		public void move1 (int x, int y)
		{

			leftC = leftC + x;
			topR = topR + y;

		}

		//Rotates piece by changing its orientation.

		public void rotate (int direction)
		{
			System.out.println("ROTATE");
			if (direction == 1)
			{
				System.out.println("NOW " + orientation);

				if (orientation == 0)
				{
					orientation = 1;
				}
				else if (orientation == 1)
				{
					orientation = 2;
				}
				else if (orientation == 2)
				{
					orientation = 3;
				}
				else if (orientation == 3)
				{
					orientation = 0;
				}
			}
			else if (direction == -1)
			{
				if (orientation == 0)
				{
					orientation = 3;
				}
				else
				{
					orientation --;
				}
			}
			System.out.println("Orientation2: " + orientation);
		}

		//Removes line(s) when they are full.

		public void removeLine (int[][] b)
		{
			boolean line = true;

			for (int j = 0; j < h; j ++)
			{
				line = true;

				for (int i = 5; i < w + 5; i ++)
				{
					if (board[i][j] == 0)
					{
						line = false;
					}
				}

				if (line)
				{
					for (int q = j; q > 0; q --)
					{
						for (int m = 5; m < w + 5; m ++)
						{
							board[m][q] = board[m][q-1];
						}
					}
				}
			}
		}
	}

	//Variables containing the location and orientation of the piece within the board.
	int topR;
	int leftC;
	int orientation;

	public void paint (Graphics g)
	{

		//Creating visible part of board.

		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				g.fillRect(i*d, j*d, d, d);
			}
		}

		//Creating lines through board.

		g.setColor(Color.GREEN);

		for (int i = 6; i < w + 6; i ++)
		{
			g.drawLine(i*d, 0, i*d, h*d);
		}

		for (int i = 1; i < h; i ++)
		{
			g.drawLine(125, i*d, w*d + 125, i*d);
		}

		p1.paint(g);

		//Paints the piece in its proper colour once it has stopped moving.

		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 1)
				{
					g.setColor(Color.RED);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 2)
				{
					g.setColor(Color.BLUE);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 3)
				{
					g.setColor(Color.GREEN);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 4)
				{
					g.setColor(Color.ORANGE);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 5)
				{
					g.setColor(Color.YELLOW);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 6)
				{
					g.setColor(Color.CYAN);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
		for (int i = 5; i < w + 5; i ++)
		{
			for (int j = 0; j < h; j ++)
			{
				if (board[i][j] == 7)
				{
					g.setColor(Color.MAGENTA);
					g.fillRect(i*d + leftC*d, j*d + topR*d, d, d);
				}
			}
		}
	}

	//Gets the piece to move or rotate as a result of key pressings.

	public void keyPressed( KeyEvent ke)
	{
		int code = ke.getKeyCode() ;

		switch (code)
		{
			case KeyEvent.VK_RIGHT : 	if(p1.checkRight(board, p1))

											p1.move1 (1, 0);

							break;

			case KeyEvent.VK_LEFT : 	if(p1.checkLeft(board, p1))

											p1.move1 (-1, 0);

							break;

			case KeyEvent.VK_UP : 		if(p1.checkUpR(board, p1))

											p1.rotate (1);

							break;

			case KeyEvent.VK_DOWN : 	if(p1.checkDownR(board, p1))

											p1.rotate (-1);

							break;
		/*
			case KeyEvent.VK_SPACE : 	p1.drop ();


							break;
		*/

			default:
		}

		repaint();
	}

	public void keyReleased( KeyEvent ke)
	{
	}

	public void keyTyped(KeyEvent ke)
	{
	}

}


package PokeMaze;

/**
 *
 * @author Hui Shan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeTile {
    private ImageIcon tile;
    private String type;
    private Image onMap;
    public JLabel label;
    private JLabel background;
    private Player player;

    public MazeTile(JLabel label, char tileRep) {
        this.label = label;
        
        // Assign image by referring character in mazeArr
        switch(tileRep) {
            case '#':
                tile = new ImageIcon("C:\\Users\\Hui Shan\\OneDrive\\Documents\\NetBeansProjects\\PokemonGame\\src\\PokeMaze\\wall (1).png");
                break;
            case '.':
                tile = new ImageIcon("C:\\Users\\Hui Shan\\OneDrive\\Documents\\NetBeansProjects\\PokemonGame\\src\\PokeMaze\\floor (1)_1.png");
                break;
            case 'G':
                tile = new ImageIcon("C:\\Users\\Hui Shan\\OneDrive\\Documents\\NetBeansProjects\\PokemonGame\\src\\PokeMaze\\gastly_1.png");
                break;
            default:
                // Handle unexpected values of tileRep here ('S' and 'E')
                tile = new ImageIcon("C:\\Users\\Hui Shan\\OneDrive\\Documents\\NetBeansProjects\\PokemonGame\\src\\PokeMaze\\floor (1)_1.png");
                break;
        }
        onMap = tile.getImage();
        player = new Player();
    }


    public Image getOnMap() {
        return onMap;
    }

    public void drawMap(int tileSize, int offsetX, int offsetY) {
    int numRows = mazeArr.length;
    int numCols = mazeArr[0].length;

    // Check if label has non-zero width and height
    if (label.getWidth() <= 0 || label.getHeight() <= 0) {
        // Handle the case where label size is not yet determined
        return;
    }

    // Calculate starting position to print the map
    int startX = (label.getWidth() - numCols * tileSize) / 2 + offsetX;
    int startY = (label.getHeight() - numRows * tileSize) / 2 + offsetY - 28;

    // Create a BufferedImage to draw on
    BufferedImage bufferedImage = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bufferedImage.createGraphics();

    // Print each tile of the map at its corresponding position
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            char tileRep = mazeArr[row][col];
            MazeTile mazeTile = new MazeTile(label, tileRep);
            g.drawImage(mazeTile.getOnMap(), startX + col * tileSize, startY + row * tileSize, tileSize, tileSize, null);
        }
    }

    // Draw the player
    int playerX = player.xCoord() * tileSize + startX;
    int playerY = player.yCoord() * tileSize + startY;
    g.drawImage(player.getPlayer(), playerX, playerY, null);

    // Dispose of the graphics object
    g.dispose();

    // Set the buffered image as the icon of the label
    label.setIcon(new ImageIcon(bufferedImage));
}


    public void movePlayer(char direction) {
        int playerX = player.xCoord();
        int playerY = player.yCoord();
        char nextTileRep;

        // Determine the next tile depending on the direction
        switch (direction) {
            case 'U':
                if (playerY > 0) {
                    nextTileRep = mazeArr[playerY - 1][playerX];
                } else {
                    // Player is at the top boundary, cannot move up
                    JOptionPane.showMessageDialog(null, "Cannot move up. You are at the top boundary.");
                    return;
                }
                break;
            case 'D':
                if (playerY < mazeArr.length - 1) {
                    nextTileRep = mazeArr[playerY + 1][playerX];
                } else {
                    // Player is at the bottom boundary, cannot move down
                    JOptionPane.showMessageDialog(null, "Cannot move down. You are at the bottom boundary.");
                    return;
                }
                break;
            case 'L':
                if (playerX > 0) {
                    nextTileRep = mazeArr[playerY][playerX - 1];
                } else {
                    // Player is at the left boundary, cannot move left
                    JOptionPane.showMessageDialog(null, "Cannot move left. You are at the left boundary.");
                    return;
                }
                break;
            case 'R':
                if (playerX < mazeArr[0].length - 1) {
                    nextTileRep = mazeArr[playerY][playerX + 1];
                } else {
                    // Player is at the right boundary, cannot move right
                    JOptionPane.showMessageDialog(null, "Cannot move right. You are at the right boundary.");
                    return;
                }
                break;
            default:
                // Invalid direction
                return;
        }

        // Check if the next tile is a wall
        if (nextTileRep == '#') {
            JOptionPane.showMessageDialog(null, "Ouch! You hit a wall. Try another direction.");
            return;
        }

        // Check if the next tile is the end of the maze
        if (nextTileRep == 'E') {
            JOptionPane.showMessageDialog(null, "Congratulations! You reached the end of the maze.");
            System.exit(0); // End the game
        }

        // Check if the next tile is a Gastly
        if (nextTileRep == 'G') {
            JOptionPane.showMessageDialog(null, "Oh no! You encountered a Gastly and got caught. \nGame Over.");
            System.exit(0); // End the game
        }
        
        // Process the move if it's valid
        boolean legalMove = isValidMove(direction);
        player.processMove(direction, legalMove);
        drawMap(28, 0, 0);  // draw the map after each movement
    }

    private boolean isValidMove(char direction) {
        int playerX = player.xCoord();
        int playerY = player.yCoord();
        int numRows = mazeArr.length;
        int numCols = mazeArr[0].length;

        switch (direction) {
            case 'U':
                return playerY > 0 && mazeArr[playerY - 1][playerX] != '#';
            case 'D':
                return playerY < numRows - 1 && mazeArr[playerY + 1][playerX] != '#';
            case 'L':
                return playerX > 0 && mazeArr[playerY][playerX - 1] != '#';
            case 'R':
                return playerX < numCols - 1 && mazeArr[playerY][playerX + 1] != '#';
            default:
                return false;
        }
    }

    public static final char[][] mazeArr = {
            "#.###############".toCharArray(),
            "#S....#.........#".toCharArray(),
            "#####.#.#.#####.#".toCharArray(),
            "#...#.#.#.....#.#".toCharArray(),
            "###.#.###G#.#.###".toCharArray(),
            "#...#...#...#...#".toCharArray(),
            "#####G#.#.#.###.#".toCharArray(),
            "#.........#.....#".toCharArray(),
            "###############E#".toCharArray(),
    };
}
package ge.map.djikra.Drawables;

import ge.map.djikra.Logic.Dijkstra;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GridCanvas extends Pane {
    //Pane is one of the simplest layout containers in JavaFX. It allows you to manually position child nodes by X and Y
    public final int rows, columnsSize, cellSize = 25;
    public final Cell[][] grid;
    public Cell startPoint = null;
    public Cell endPoint = null;

    public GridCanvas(int rows, int cols) {
        this.rows = rows;
        this.columnsSize = cols;
        this.grid = new Cell[rows][cols];
        setPrefSize(cols * cellSize, rows * cellSize);
        Image bgImage = new Image("file:/C:/Users/nnica/OneDrive/Desktop/TEST/tEST alurwe/Dijkstras_Algorithm_Visualizer/src/main/resources/Images/Background_img.jpg");

        BackgroundSize backgroundSize = new BackgroundSize(
                100, 100, true, true, false, false
        );

        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        this.setBackground(new Background(backgroundImage));
        drawGrid();
    }

    public void drawGrid() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columnsSize; column++) {
                Rectangle wholeRectangle = new Rectangle(column * cellSize, row * cellSize, cellSize, cellSize);
                //v Xcoordinate from where to start , v1 Ycoordinate where to start ,cellsize zoma
                wholeRectangle.setStroke(Color.GRAY);
                wholeRectangle.setFill(Color.color(1, 1, 1, 0.1));//
                Cell cell = new Cell(row, column, wholeRectangle);
                grid[row][column] = cell;

                wholeRectangle.setOnMouseClicked(clickOnMouse -> {
                    if (clickOnMouse.getButton() == MouseButton.PRIMARY) {
                        if (startPoint == null) {
                            startPoint = cell;
                            wholeRectangle.setFill(Color.GREEN);
                        } else if (endPoint == null && cell != startPoint) {
                            endPoint = cell;
                            wholeRectangle.setFill(Color.RED);
                            findShortestPathUntilRedMark();
                        }
                    } else if (clickOnMouse.getButton() == MouseButton.SECONDARY) {
                        if (cell != startPoint && cell != endPoint) {
                            cell.isWall = !cell.isWall;
                            wholeRectangle.setFill(cell.isWall ? Color.GRAY : Color.WHITE);
                        }
                    }
                });

                getChildren().add(wholeRectangle);
            }
        }
    }


    public void findShortestPathUntilRedMark() {
        List<Cell> path = Dijkstra.findClosestPath(grid, startPoint, endPoint);
        if (path == null) return;

        for (Cell cell : path) {
            if (cell != startPoint && cell != endPoint) {
                cell.rect.setFill(Color.MAGENTA);
            }
        }
    }

    public static class Cell {
        int currentStandingRow, currentStandingColumn;
        boolean isWall = false;
        Rectangle rect;

        public Cell(int row, int col, Rectangle rect) {
            this.currentStandingRow = row;
            this.currentStandingColumn = col;
            this.rect = rect;
        }

        public List<Cell> getNeighbors(Cell[][] grid) {
            //grid 2d array
            //  {1, 0}: Down
            //  {-1, 0}: Up
            //  {0, 1}: Right
            //  {0, -1}: Left
            List<Cell> neighbors = new ArrayList<>();
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] direction : directions) {
                int newRow = currentStandingRow + direction[0];
                int newColumn = currentStandingColumn + direction[1];
                if (newRow >= 0 && newColumn >= 0 && newRow < grid.length && newColumn < grid[0].length) {
                    Cell neighbourCell = grid[newRow][newColumn];
                    if (!neighbourCell.isWall) neighbors.add(neighbourCell);
                }
            }
            return neighbors;
        }
    }
}

package edu.hw2;

public class Task2 {
    public static class Rectangle {
        protected int width;
        protected int height;

        public Rectangle() {
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Rectangle setWidth(int width) {
            return new Rectangle(width, this.height);
        }

        public Rectangle setHeight(int height) {
            return new Rectangle(this.width, height);
        }

        public double area() {
            return width * height;
        }
    }


    public static class Square extends Rectangle {
        public Square(){
            super();
        }
        public Square(int side){
            super(side, side);
        }
        public Square setSide(int side){
            return new Square(side);
        }
    }
}

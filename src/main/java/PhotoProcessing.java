import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

class PhotoProcessing {
    protected static BufferedImage toBlackAndWhite (BufferedImage image){// Настроить
        double medianaLumen = 0;
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                int p = image.getRGB(i, j);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                double l = (float) (0.299 * r + 0.587 * g + 0.114 * b);
                medianaLumen += l;
            }
        double mLumen = medianaLumen / (width * height);

        for ( int i = 0; i < width; i++)
            for ( int j = 0; j < height; j++){
                int p = image.getRGB(i, j);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                float lumen = (float) (0.299*r + 0.587*g + 0.114*b);

                if (lumen < mLumen)
                    p = (a<<24) | (0<<16) | (0<<8) | (0);
                else
                    p = (a<<24) | (255<<16) | (255<<8) | (255);

                image.setRGB(i, j, p);
            }


        return image;
    }

    protected static BufferedImage BradlyAlgorithm (BufferedImage image){
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        final int S = width / 8;
        final int s2 = S / 2;
        float t = (float) 0.15;
        long[]integral_image;
        long sum = 0;
        int count = 0;
        int index;
        int x1, y1, x2, y2;
        byte[] src;
        byte[] res;

        //выход и ввод пока одинаковые. создаем по одному изображению, для одинаковых размеров
        src = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        res= ((DataBufferByte) image.getRaster().getDataBuffer()).getData();


        //рассчитываем интегральное изображение
        integral_image = new long [width*height*Long.SIZE];

        for (int i = 0; i < width; i++) {
            sum = 0;
            for (int j = 0; j < height; j++) {
                index = j * width + i;
                sum += src[index];
                if (i==0)
                    integral_image[index] = sum;
                else
                    integral_image[index ] = integral_image[index-1] + sum;
            }
        }

        //находим границы для локальные областей
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                index = j  + i;

                x1=i-s2;
                x2=i+s2;
                y1=j-s2;
                y2=j+s2;

                if (x1 < 0)
                    x1 = 0;
                if (x2 >= width)
                    x2 = width-1;
                if (y1 < 0)
                    y1 = 0;
                if (y2 >= height)
                    y2 = height-1;

                count = (x2-x1)*(y2-y1);

                sum = integral_image[x2+y2*width] - integral_image[x2+y1*width]-
                        integral_image[x1+y2*width] + integral_image[x1+y1*width];
                if ((long)(src[index]*count) < (long)(sum*(1.0-t)))
                    res[index] =-127;
                else
                    res[index] = +127;
            }
        }

        BufferedImage image1 = new BufferedImage(width, height, image.getType());
        image1.getRaster().setDataElements(0, 0, width, height, res);
        return  image1;
    }
}

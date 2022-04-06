package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import deque.ArrayDeque;

public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> keyArray = new ArrayDeque<>();
        double sound;
        for (int i =0; i < keyboard.length(); i += 1) {
            sound = 440.0 * Math.pow(2, (i - 24) / 12.0);
            GuitarString stringSound = new GuitarString(sound);
            keyArray.addLast(stringSound);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int ind = keyboard.indexOf(key);
                if (ind >= 0 && ind < 37) {
                    keyArray.get(ind).pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i += 1) {
                sample += keyArray.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < keyboard.length(); i += 1) {
                keyArray.get(i).tic();
            }
        }
    }
}


package com.examples.threadlocal;

/**
 * Created by JackZhang on 17/7/23.
 */
public class TestClient {


    public static void main(String[] args) {
        SequenceNumber sq = new SequenceNumber();

        Thread jack = new Thread(new SimpleWorker(sq));
        Thread tom = new Thread(new SimpleWorker(sq));
        Thread lee = new Thread(new SimpleWorker(sq));
        Thread mona = new Thread(new SimpleWorker(sq));


        jack.start();
        mona.start();
        tom.start();
        lee.start();

    }


    private static class SimpleWorker implements Runnable {

        private SequenceNumber sequenceNumber;

        public SimpleWorker(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++){
                System.out.println("Thread - " + Thread.currentThread().getName() +
                        " Sequence = " + sequenceNumber.getNextSeq());
            }

        }
    }

}



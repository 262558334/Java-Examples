package com.examples.threadlocal;

/**
 * Created by JackZhang on 17/7/23.
 */
public class SequenceNumber {

    private ThreadLocal<Integer> sequenceNumber = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextSeq(){
        sequenceNumber.set(sequenceNumber.get()+1);
        return sequenceNumber.get();
    }
}

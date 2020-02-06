package com.company.firsttask.patterns.adapter;

public class CardReader implements USB {

    private MemoryCard memoryCard;

    public CardReader(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public void connectViaCable() {
        this.memoryCard.insert();
        this.memoryCard.copyData();
    }
}

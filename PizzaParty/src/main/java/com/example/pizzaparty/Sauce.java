package com.example.pizzaparty;

public enum Sauce {
    TOMATO{
        @Override
        public String toString(){
            return "Tomato";
        }
    },
    ALFREDO{
        @Override
        public String toString(){
            return "Alfredo";
        }
    },
}

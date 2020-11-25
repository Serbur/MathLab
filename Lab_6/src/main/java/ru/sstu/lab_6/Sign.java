package ru.sstu.lab_6;

public enum Sign {
    PLUS {
        @Override
        public Sign reverse() {
            return MINUS;
        }
    }, MINUS {
        @Override
        public Sign reverse() {
            return PLUS;
        }
    };

    public abstract Sign reverse();
}

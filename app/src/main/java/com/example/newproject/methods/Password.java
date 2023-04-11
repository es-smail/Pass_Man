package com.example.newproject.methods;

import java.sql.Timestamp;



    public  class Password implements Comparable<Password> {
        private int id;
        private String password;
        private Timestamp timestamp;

        public Password(int id, String password, Timestamp timestamp) {
            this.id = id;
            this.password = password;
            this.timestamp = timestamp;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Password other) {
            return this.timestamp.compareTo(other.timestamp);
        }
    }


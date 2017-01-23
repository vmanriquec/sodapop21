package com.food.sistemas.sodapopapp.modelo;
public class cambiomesa {
        private int mesaantes;
        private int mesadespues;
        public cambiomesa(int mesaantes,int mesadespues)
        {
            super();
            this.mesaantes=mesaantes;
            this.mesadespues=mesadespues;
             }
        public int getMesaantes() {
            return mesaantes;
        }
        public void setMesaantes(int mesaantes) {
            this.mesaantes = mesaantes;
        }
        public int getMesadespues() {
            return mesadespues;
        }
        public void setMesadespues(int mesadespues) {
            this.mesadespues = mesadespues;
        }
        @Override
        public String toString(){
            return String.valueOf(mesaantes)+" cambio a #-"+ String.valueOf(mesadespues);
        }
}
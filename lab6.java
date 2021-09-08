//classe da estrutura de dados (recurso) compartilhado entre as threads
class Contador {
  //recurso compartilhado
  private int pares;
  private int numT;
  private int[] vet;
  //construtor
  public Contador(int nThreads, int[] vetor) {
     this.pares = 0;
     this.numT = nThreads;
     this.vet = vetor;
  }

  public synchronized void incPares() {
      this.pares++;}
  public synchronized int getPares() {
      return this.pares;}

  public synchronized int getNumT() {
      return this.numT;}

  public synchronized int[] getVet() {
      return this.vet;}
}

//classe que estende Thread e implementa a tarefa de cada thread do programa
class T extends Thread {
   //identificador da thread
   private int id;
   //objeto compartilhado com outras threads
   Contador c;

   //construtor
   public T(int tid, Contador cont) {
      this.id = tid;
      this.c = cont;
   }

   //metodo main da thread
   public void run() {
      for (int i=id; i<this.c.getVet().length; i+=this.c.getNumT()) {
         if(this.c.getVet()[i]%2 == 0) this.c.incPares();
      }
   }
}

//classe da aplicacao
class lab6 {
   static final int nThreads = 2;
   static final int tamVetor = 10000000;

   public static void main (String[] args) {
      //reserva espaço para um vetor de threads
      Thread[] threads = new Thread[nThreads];

      //reserva espaço para um vetor de inteiros
      int[] vetor = new int[tamVetor];

      //inicializa tamVetor
      for(int i=0 ; i<tamVetor ; i++){
        vetor[i] = i;
      }

      //cria uma instancia do recurso compartilhado entre as threads
      Contador cont = new Contador(nThreads,vetor);

      //cria as threads da aplicacao
      for (int i=0; i<threads.length; i++) {
         threads[i] = new T(i, cont);
      }

      //inicia as threads
      for (int i=0; i<threads.length; i++) {
         threads[i].start();
      }

      //espera pelo termino de todas as threads
      for (int i=0; i<threads.length; i++) {
         try { threads[i].join(); } catch (InterruptedException e) { return; }
      }

      System.out.println("Quantidade de números pares = " + cont.getPares());
   }
}

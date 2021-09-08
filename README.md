# LAB 6

**Atividade 1**

3.  No momento em que o start() dispara as threads não é possível determinar a sua ordem de execução. Isso só seria possível se houvesse alguma forma de sincronização das threads.

5. O trecho que estava comentado realizava a espera do término das threads para então dar sequência ao fluxo principal. Portanto, a única mudança que ocorreu na prática foi que a mensagem "Terminou" é garantidamente exibida por último.

**Atividade 3**

1. A seção crítica está na linha 54 do código, onde s é incrementado

   ```java
   this.s.inc();
   ```

   O esperado é que o resultado fosse 200000, pois cada thread faz 100000 incrementos.

2. Os resultados não foram como esperado, pois não havia garantia de que apenas uma thread por vez entraria na seção crítica. Com isso, muitos dos incrementos foram feitos com as duas threads utilizando o mesmo valor para a variável r, e isso fez com que o incremento em r fosse de apenas uma unidade ao invés de duas.

**Atividade 4**

3. Os valores impressos foram o esperado, pois o syncronized permite que apenas uma thread por vez entre na seção de código entre colchetes. É o equivalente ao "lock" e "unlock" em C.


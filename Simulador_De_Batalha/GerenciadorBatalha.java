package pokesal;

/*
 * Classe que gerencia as batalhas pokesal.
 */
public class GerenciadorBatalha {

  /*
   * Calcula o dano causado durante uma batalha.
   */
  public int calcularDano(Pokesal atacante, Pokesal defensor, Terreno terreno) {
    double multiplicadorElemental = atacante.getTipo().getVantagem(defensor.getTipo());
    double modificadorTerreno = 1.0;

    if (terreno == Terreno.ASFALTO_QUENTE && atacante.getTipo() == TipoElemental.FOGO) {
      modificadorTerreno = 1.15;
    } else if (terreno == Terreno.POCA_CHUVA && atacante.getTipo() == TipoElemental.AGUA) {
      modificadorTerreno = 1.10;
    }
    int danoBase = atacante.getAtkEfetivo() - (defensor.getdef() / 2);
    danoBase = Math.max(1, danoBase);
    return (int) (danoBase * multiplicadorElemental * modificadorTerreno);
  }

  /*
   * Define qual treinador pokesal vai atacar primeiro.
   */
  public void executarTurno(Pokesal p1, Pokesal p2, Terreno terreno) {
    Pokesal primeiro = (p1.getAtkEfetivo() >= p2.getAtkEfetivo()) ? p1 : p2;
    Pokesal segundo = (primeiro == p1) ? p2 : p1;

    if (!primeiro.estaDerrotado()) {
      int dano = calcularDano(primeiro, segundo, terreno);
      segundo.receberDano(dano);
      System.out.println(primeiro.getNome() + "causou " + dano + "de dano em " + segundo.getNome());
    }
    if (!segundo.estaDerrotado()) {
      int dano = calcularDano(segundo, primeiro, terreno);
      primeiro.receberDano(dano);
      System.out.println(segundo.getNome() + "causou " + dano + "de dano em " + primeiro.getNome());
    }

    p1.aplicarEfeitoFimTurno(terreno);
    p2.aplicarEfeitoFimTurno(terreno);
  }
}
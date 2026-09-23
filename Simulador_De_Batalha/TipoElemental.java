package pokesal;

/**
 * Representa os tipos elementais do jogo.
 */
public enum TipoElemental {
  FOGO, AGUA, PLANTA;

  double getVantagem(TipoElemental tipo) {
    return 0;
  }
}
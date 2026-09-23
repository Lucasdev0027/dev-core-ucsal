package pokesal;

/*
 * Classe Principal.
 */
public class Main {

  /*
 * Introdução ao evento PokeSal.
 */
  public static void main(String[] args) {
    System.out.println("=== TORNEIO OFICIAL POKÉSAL - UCSAL PITUAÇU ===");
    System.out.println("------------------------------------------------");

    Pokesal charSal = new Pokesal("CharSal", 100, 30, 15, 25, TipoElemental.FOGO);
    Pokesal bulbaSal = new Pokesal("BulbaSal", 120, 22, 25, 15, TipoElemental.PLANTA);

    Terreno terrenoDoDia = Terreno.CANTEIRO_CENTRAL;
    System.out.println("Efeito de Terreno Ativo: " + terrenoDoDia);
    System.out.println("------------------------------------------------");

    charSal.setStatusAtual(StatusEfeito.QUEIMADO);
    System.out.println("ALERTA " + charSal.getNome() + " começou a batalha QUEIMADO!");
    System.out.println("------------------------------------------------");

    GerenciadorBatalha arena = new GerenciadorBatalha();
    int rodada = 1;

    while (!charSal.estaDerrotado() && !bulbaSal.estaDerrotado()) {
      System.out.println("\n--- TURNO " + rodada + " ---");
      System.out.println(charSal.getNome() + " HP: " + charSal.hpAtual());
      System.out.println(bulbaSal.getNome() + " HP: " + bulbaSal.hpAtual());
      System.out.println("................................................");

      arena.executarTurno(charSal, bulbaSal, terrenoDoDia);
      rodada++;
      if (rodada > 20) {
        System.out.println("A batalha demorou demais! Empate técnico.");
        break;
      }
    }
    System.out.println("\n================================================");
    System.out.println("=== FIM DA BATALHA ===");
    if (charSal.estaDerrotado() && bulbaSal.estaDerrotado()) {
      System.out.println("Ambos os Pokésals desmaiaram ao mesmo tempo!");
    } else if (charSal.estaDerrotado()) {
      System.out.println(bulbaSal.getNome() + " venceu a batalha no Estacionamento da UCSal!");
    } else {
      System.out.println(charSal.getNome() + " venceu a batalha no Estacionamento da UCSal!");
    }
    System.out.println("================================================");
  }
}
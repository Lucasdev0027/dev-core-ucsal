package pokesal;

/*
 * Classe pokesal.
 */
public class Pokesal {
  private String nome;
  private int hpMax;
  private int hpAtual;
  private int atk;
  private int def;
  private int spd;
  private TipoElemental tipo;
  private StatusEfeito statusAtual;

  /*
   * Informações do pokesal.
   */
  public Pokesal(String nome, int hp, int atk, int def, int spd, TipoElemental tipo) {
    this.nome = nome;
    this.hpMax = hp;
    this.hpAtual = hp;
    this.atk = atk;
    this.def = def;
    this.spd = spd;
    this.tipo = tipo;
    this.statusAtual = StatusEfeito.NENHUM;

  }

  public int getSpdEfetiva() {
    return this.statusAtual == StatusEfeito.PARALISADO ? (int) (this.spd * 0.5) : this.spd;
  }

  public int getAtkEfetivo() {
    return this.statusAtual == StatusEfeito.QUEIMADO ? (int) (this.atk * 0.75) : this.atk;
  }
/*
 * Contabiliza o dano recebido no pokesal.
 */
  public void receberDano(int dano) {
    this.hpAtual = Math.max(0, this.hpAtual - dano);
  }
/*
 * Contabiliza a cura no pokesal.
 */
  public void curar(int quantidade) {
    this.hpAtual = Math.min(this.hpMax, this.hpAtual + quantidade);
  }
/*
 * Efeito aplicado no terreno da batalha.
 */
  public void aplicarEfeitoFimTurno(Terreno terreno) {
    if (terreno == Terreno.CANTEIRO_CENTRAL && this.tipo == TipoElemental.PLANTA) {
      int cura = (int) (this.hpMax * 0.05);
      curar(cura);
      System.out.println(nome + "recuperou " + cura + "HP pelo Canteiro Central.");
    }

    switch (this.statusAtual) {
      case QUEIMADO:
        receberDano(5);
        System.out.println(nome + " perdeu 5 HP devido a queimadura");
        break;
      case ENVENENADO:
        receberDano(8);
        System.out.println(nome + " perdeu 7 HP pelo envenenamento");
        break;
      default:
        break;
    }

  }

  public String getNome() {
    return nome;
  }
/*
 * Vida do pokesal.
 */
  public int hpAtual() {
    return hpAtual;
  }
/*
 * Pokesal foi derrotado.
 */
  public boolean estaDerrotado() {
    return hpAtual <= 0;
  }

  public TipoElemental getTipo() {
    return tipo;
  }

  public void setStatusAtual(StatusEfeito status) {
    this.statusAtual = status;
  }
/*
 * Defesa do pokesal.
 */
  public int getdef() {
    return 0;
  }
}

package teste.producer.controller.mensagem;

import java.io.Serializable;
import java.util.Date;

public class Mensagem implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6491449962350888098L;
    private String nome;
    private String conteudo;
    private String teor;
    private Long codigo;
    private Date horario;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public String getTeor() {
        return teor;
    }
    public void setTeor(String teor) {
        this.teor = teor;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Date getHorario() {
        return horario;
    }
    public void setHorario(Date horario) {
        this.horario = horario;
    }
    
    

}

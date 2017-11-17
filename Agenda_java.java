 package agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Agenda {

    public static Connection conexao;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static ResultSet rst;
    public static ResultSet r;
    public static String sql;

    public static void main(String[] args) throws SQLException {

        int id;
        String nome;
        String sobrenome;
        String idade;
        String sexo;
        String telefone;
        String fax;
        String email;
        String endereco;
        String estado;
        String cidade;
        
	
        Scanner a = new Scanner(System.in);

        System.out.println("Digite o Id");
        id = Integer.parseInt(a.nextLine());

        System.out.println("Digite o Nome");
        nome = a.nextLine();

        System.out.println("Digite o sobrenome");
        sobrenome = a.nextLine();
        
        System.out.println("Digite a idade");
        idade = a.nextLine();

        System.out.println("Digite o sexo");
        sexo = a.nextLine();
       
        System.out.println("Digite o Id");
        id = Integer.parseInt(a.nextLine());
        
        System.out.println("Digite o telefone");
        telefone = a.nextLine();
        
        System.out.println("Digite o fax");
        fax = a.nextLine();
        
        System.out.println("Digite o email");
        email = a.nextLine();
        
        System.out.println("Digite o Id");
        id = Integer.parseInt(a.nextLine());
        
        System.out.println("Digite o endereco");
        endereco = a.nextLine();

        System.out.println("Digite o estado");
        estado = a.nextLine();

        System.out.println("Digite a cidade");
        cidade = a.nextLine();
        
        String usuario = "root";
        String senha = "";
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);

        sql = "INSERT INTO cadastro (id, nome, sobrenome, idade, sexo) VALUES (?, ?, ?, ?, ?)";
        ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, nome);
        ps.setString(3, sobrenome);
    	ps.setString(4, idade);
        ps.setString(5, sexo);
      

	ps.execute();
        ps.close();
        conexao.close();
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);

        sql = "INSERT INTO contato (id, telefone, fax, email) VALUES (?, ?, ?, ?)";
        ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, telefone);
        ps.setString(3, fax);
        ps.setString(4, email);

        ps.execute();
        ps.close();
        conexao.close();
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);

        sql = "INSERT INTO endereco (id, endereco, estado, cidade) VALUES (?, ?, ?, ?)";
        ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, endereco);
        ps.setString(3, estado);
        ps.setString(4, cidade);

        ps.execute();
        ps.close();
        conexao.close();
        
        System.out.println("\n\nListando os Registros Gravados");
        
        // Leitura do Banco de Dados
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);
        sql   = "select * from cadastro inner join contato ON contato.id = cadastro.id inner join endereco ON endereco.id = cadastro.id"; 
        ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
       
        while (rs.next()) {
            System.out.println("Id..:" + rs.getInt("id"));
            System.out.println("Nome:" + rs.getString("nome"));
            System.out.println("sobrenome.:" + rs.getString("sobrenome"));
	    System.out.println("idade..:" + rs.getString("idade"));
            System.out.println("sexo:" + rs.getString("sexo"));
            System.out.println("");	
        }
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);
        sql   = "select * from contato inner join cadastro ON cadastro.id = contato.id inner join endereco ON endereco.id = contato.id";
        ps = conexao.prepareStatement(sql);
        ResultSet rst = ps.executeQuery();
         
        while (rst.next()){
            System.out.println("id..:" + rst.getInt("id"));
            System.out.println("telefone..:" + rst.getString("telefone"));
            System.out.println("fax:" + rst.getString("fax"));
            System.out.println("email.:" + rst.getString("email"));
            System.out.println("");	
             
         }
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", usuario, senha);
         sql   = "select * from endereco inner join cadastro ON cadastro.id = endereco.id inner join contato ON contato.id = endereco.id";
         ps = conexao.prepareStatement(sql);
        ResultSet r = ps.executeQuery();
        
        while (r.next()){
            System.out.println("id..:" + r.getInt("id"));
            System.out.println("endereco..:" + r.getString("endereco"));
            System.out.println("estado:" + r.getString("estado"));
            System.out.println("cidade.:" + r.getString("cidade"));
            System.out.println("");	
             
         }
        
    }

} 

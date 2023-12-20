package model.view;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Animal;
import model.CondicoesEspeciais;
import model.Consulta;
import model.Raca;
import model.Tratamento;
import model.Usuario;
import model.dao.AnimalDao;
import model.dao.CondicoesEspeciaisDao;
import model.dao.ConsultaDao;
import model.dao.RacaDao;
import model.dao.TratamentoDao;

import model.dao.UsuarioDao;
import model.e.ETipoAnimal;
import model.e.ETipoSexo;
import model.e.ETipoUsuario;

public class Principal extends javax.swing.JFrame {
    ArrayList<Usuario> ListaUsuario = new ArrayList<>(UsuarioDao.buscarTodos());
    ArrayList<Animal> ListaAnimal = new ArrayList<>(AnimalDao.buscarTodos());
    ArrayList<Raca> ListaRaca = new ArrayList<>(RacaDao.buscarTodos());
    ArrayList<CondicoesEspeciais> ListaCondicoesEspeciais = new ArrayList<>(CondicoesEspeciaisDao.buscarTodos());
    ArrayList<Consulta> ListaConsulta = new ArrayList<>(ConsultaDao.buscarTodos());
    ArrayList<Tratamento> ListaTratamento = new ArrayList<>(TratamentoDao.buscarTodos());
      
    String modoUsuario = "";
    String modoAnimal = "";
    String modoRaca = "";
    String modoCondicoesEspeciais = "";
    String modoConsulta = "";
    String modoTratamento = "";
   
    private void LimparCampos(){        
        /*Usuarios*/
        c_usuario_cod.setText("");
        c_usuario_cpf.setText("");
        c_usuario_email.setText("");
        c_usuario_nome.setText("");
        c_usuario_senha.setText("");
        
        /*Animais*/
        c_animal_cod.setText("");
        c_animal_cpfDono.setText("");
        c_animal_idade.setText("");
        c_animal_nome.setText("");
        c_animal_peso.setText("");
        
        /*Raças*/
        c_raca_cod.setText("");
        c_raca_nome.setText("");
        
        /*Condições Especiais*/
        c_condicao_cod.setText("");
        c_condicao_alergia.setText("");
        c_condicao_prob.setText("");
        
        /*Consulta*/
        c_consulta_cod.setText("");
        c_consulta_data.setText("");
        c_consulta_exames.setText("");
        
        /*Tratamento*/
        c_tratamento_cod.setText("");
        c_tratamento_medicamento.setText("");
        c_tratamento_procedimento.setText("");
    }
    
    private void LoadTableUsuarios(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código","Nome", "Sexo", "CPF", "E-mail", "Senha", "Tipo"},0);
            
        for(int i=0;i<ListaUsuario.size();i++){
            Object linha[] = new Object[]{ListaUsuario.get(i).getId(),
                                          ListaUsuario.get(i).getNome(),
                                          ListaUsuario.get(i).getSexo().name(),
                                          ListaUsuario.get(i).getCpf(),
                                          ListaUsuario.get(i).getEmail(),
                                          ListaUsuario.get(i).getSenha(),
                                          ListaUsuario.get(i).getTipoUsuario().name()};
            modelo.addRow(linha);
        }
        
        tbl_usuarios.setModel(modelo);
        tbl_usuarios.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbl_usuarios.getColumnModel().getColumn(6).setPreferredWidth(50);    
    }
 
    private void LoadTableAnimal(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Nome", "Sexo", "Idade", "Peso", "Raça", "CPF do Dono"}, 0);

        for (int i = 0; i < ListaAnimal.size(); i++) {
                Animal animal = ListaAnimal.get(i);
                Raca raca = animal.getRaca();

                String nomeRaca = (raca != null) ? raca.getNome() : "Raça não especificada";

                Object linha[] = new Object[]{animal.getId(),
                        animal.getNome(),
                        animal.getSexo(),
                        animal.getIdade(),
                        animal.getPeso(),
                        nomeRaca,
                        animal.getCpfDono()};
                modelo.addRow(linha);
            }

        tbl_animais.setModel(modelo);
        tbl_animais.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_animais.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_animais.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_animais.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_animais.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_animais.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_animais.getColumnModel().getColumn(6).setPreferredWidth(150);
    }
    
    private void LoadTableRaca(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Codigo","Espécie", "Nome"},0);
            
        for(int i=0;i<ListaRaca.size();i++){
            Object linha[] = new Object[]{ListaRaca.get(i).getId(),
                                          ListaRaca.get(i).getTipoAnimal().name(),
                                          ListaRaca.get(i).getNome()};
            modelo.addRow(linha);
        }
        
        tbl_raca.setModel(modelo);
        tbl_raca.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_raca.getColumnModel().getColumn(1).setPreferredWidth(150);    
        tbl_raca.getColumnModel().getColumn(2).setPreferredWidth(150);   
    }
    
    private void LoadTableCondicoesEspeciais() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Raça", "Alergias", "Problemas de Saúde"}, 0);

        for (int i = 0; i < ListaCondicoesEspeciais.size(); i++) {
            CondicoesEspeciais condicoes = ListaCondicoesEspeciais.get(i);
            Raca raca = condicoes.getRaca();

            String nomeRaca = (raca != null) ? raca.getNome() : "Raça não especificada";

            Object linha[] = new Object[]{condicoes.getId(),
                    nomeRaca,
                    condicoes.getAlergias(),
                    condicoes.getProblemasDeSaude()};
            modelo.addRow(linha);
        }

        tbl_condicoes.setModel(modelo);
        tbl_condicoes.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_condicoes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_condicoes.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_condicoes.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

    private void LoadTableConsulta(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Data", "Exames", "Animal"}, 0);

        for (int i = 0; i < ListaConsulta.size(); i++) {
            Consulta consulta = ListaConsulta.get(i);
            Animal animal = consulta.getAnimal();

            String nomeAnimal = (animal != null) ? animal.getNome() : "Animal não especificado";

            Object linha[] = new Object[]{consulta.getId(),
                    consulta.getData(),
                    consulta.getExames(),
                    nomeAnimal};
            modelo.addRow(linha);
        }

        tbl_consulta.setModel(modelo);
        tbl_consulta.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_consulta.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_consulta.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_consulta.getColumnModel().getColumn(3).setPreferredWidth(150);
    }
    
    private void LoadTableTratamento(){
     DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Codigo","Consulta" , "Medicamentos","Procedimentos"},0);

     for(int i=0;i<ListaTratamento.size();i++){
         Object linha[] = new Object[]{ListaTratamento.get(i).getId(),
                                       ListaTratamento.get(i).getConsulta(),
                                       ListaTratamento.get(i).getMedicamentos(),
                                       ListaTratamento.get(i).getProcedimentos()};
         modelo.addRow(linha);
     }

     tbl_tratamento.setModel(modelo);
     tbl_tratamento.getColumnModel().getColumn(0).setPreferredWidth(100);
     tbl_tratamento.getColumnModel().getColumn(1).setPreferredWidth(150);
     tbl_tratamento.getColumnModel().getColumn(1).setPreferredWidth(150);
     tbl_tratamento.getColumnModel().getColumn(1).setPreferredWidth(150);
 }
    
    public Principal() {
        initComponents();
        jTabbedPane1.setEnabled(false);   
        bloquearCamposId();         
    }
    
    private void bloquearCamposId(){
        c_usuario_cod.setEnabled(false);
        c_animal_cod.setEnabled(false);
        c_raca_cod.setEnabled(false);
        c_condicao_cod.setEnabled(false);
        c_consulta_cod.setEnabled(false);
        c_tratamento_cod.setEnabled(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_Login = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        c_login_user = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        c_login_password = new javax.swing.JPasswordField();
        btn_login_entrar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel_Usuarios = new javax.swing.JPanel();
        ScrollPane_Usuarios = new javax.swing.JScrollPane();
        tbl_usuarios = new javax.swing.JTable();
        jPanel_Movimentacoes_Usuarios = new javax.swing.JPanel();
        label_codigo = new javax.swing.JLabel();
        label_nome = new javax.swing.JLabel();
        label_sexo = new javax.swing.JLabel();
        label_cpf = new javax.swing.JLabel();
        label_email = new javax.swing.JLabel();
        label_senha = new javax.swing.JLabel();
        label_acesso = new javax.swing.JLabel();
        c_usuario_cod = new javax.swing.JTextField();
        c_usuario_nome = new javax.swing.JTextField();
        c_usuario_email = new javax.swing.JTextField();
        btn_usuario_salvar = new javax.swing.JButton();
        btn_usuario_cancelar = new javax.swing.JButton();
        c_usuario_cpf = new javax.swing.JFormattedTextField();
        c_usuario_senha = new javax.swing.JPasswordField();
        cb_usuario_tipoAcesso = new javax.swing.JComboBox<>();
        cb_usuario_sexo = new javax.swing.JComboBox<>();
        btn_usuario_novo = new javax.swing.JButton();
        btn_usuario_editar = new javax.swing.JButton();
        btn_usuario_excluir = new javax.swing.JButton();
        jPanel_Racas = new javax.swing.JPanel();
        ScrollPane_Racas = new javax.swing.JScrollPane();
        tbl_raca = new javax.swing.JTable();
        jPanel_Movimentacoes_Racas = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        c_raca_cod = new javax.swing.JTextField();
        c_raca_nome = new javax.swing.JTextField();
        btn_raca_salvar = new javax.swing.JButton();
        btn_raca_cancelar = new javax.swing.JButton();
        cb_tipoAnimal1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btn_raca_novo = new javax.swing.JButton();
        btn_raca_editar = new javax.swing.JButton();
        btn_raca_excluir = new javax.swing.JButton();
        jPanel_Animais = new javax.swing.JPanel();
        ScrollPane_Animais = new javax.swing.JScrollPane();
        tbl_animais = new javax.swing.JTable();
        jPanel_Movimentacoes_Animais = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        c_animal_cod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        c_animal_nome = new javax.swing.JTextField();
        btn_animal_salvar = new javax.swing.JButton();
        btn_animal_cancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        c_animal_idade = new javax.swing.JTextField();
        c_animal_peso = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cb_tipoSexo2 = new javax.swing.JComboBox();
        c_animal_cpfDono = new javax.swing.JFormattedTextField();
        cb_idRaca3 = new javax.swing.JComboBox();
        btn_animal_editar = new javax.swing.JButton();
        btn_animal_excluir = new javax.swing.JButton();
        btn_animal_novo = new javax.swing.JButton();
        jPanel_Condicoes = new javax.swing.JPanel();
        ScrollPane_Condicoes = new javax.swing.JScrollPane();
        tbl_condicoes = new javax.swing.JTable();
        jPanel_Movimentacoes_Condicoes = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        c_condicao_cod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        c_condicao_alergia = new javax.swing.JTextField();
        btn_condicaoEspecial_salvar = new javax.swing.JButton();
        btn_condicaoEspecial_cancelar = new javax.swing.JButton();
        c_condicao_prob = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cb_idRaca2 = new javax.swing.JComboBox();
        btn_condicaoEspecial_novo = new javax.swing.JButton();
        btn_condicaoEspecial_editar = new javax.swing.JButton();
        btn_condicaoEspecial_excluir = new javax.swing.JButton();
        jPanel_Consulta = new javax.swing.JPanel();
        ScrollPane_Consulta = new javax.swing.JScrollPane();
        tbl_consulta = new javax.swing.JTable();
        jPanel_Movimentacoes_Consulta = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        c_consulta_cod = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btn_consulta_salvar = new javax.swing.JButton();
        btn_consulta_cancelar = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        c_consulta_data = new javax.swing.JFormattedTextField();
        c_consulta_exames = new javax.swing.JTextField();
        cb_consulta_animal = new javax.swing.JComboBox();
        btn_consulta_novo = new javax.swing.JButton();
        btn_consulta_editar = new javax.swing.JButton();
        btn_consulta_excluir = new javax.swing.JButton();
        jPanel_Tratamento = new javax.swing.JPanel();
        ScrollPane_Tratamento = new javax.swing.JScrollPane();
        tbl_tratamento = new javax.swing.JTable();
        jPanel_Movimentacoes_Tratamento = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        c_tratamento_cod = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_tratamento_salvar = new javax.swing.JButton();
        btn_tratamento_cancelar = new javax.swing.JButton();
        c_tratamento_procedimento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        c_tratamento_medicamento = new javax.swing.JTextField();
        cb_tratamento_idConsulta = new javax.swing.JComboBox();
        btn_tratamento_novo = new javax.swing.JButton();
        btn_tratamento_editar = new javax.swing.JButton();
        btn_tratamento_excluir = new javax.swing.JButton();

        jLabel20.setText("jLabel20");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("LOGIN");

        c_login_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_login_userActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("SENHA");

        btn_login_entrar.setText("ENTRAR");
        btn_login_entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_login_entrarMouseClicked(evt);
            }
        });
        btn_login_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login_entrarActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe Script", 0, 36)); // NOI18N
        jLabel27.setText("Petshop Cão e Gato");

        javax.swing.GroupLayout jPanel_LoginLayout = new javax.swing.GroupLayout(jPanel_Login);
        jPanel_Login.setLayout(jPanel_LoginLayout);
        jPanel_LoginLayout.setHorizontalGroup(
            jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LoginLayout.createSequentialGroup()
                .addGroup(jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_LoginLayout.createSequentialGroup()
                        .addGroup(jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_LoginLayout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel27))
                            .addGroup(jPanel_LoginLayout.createSequentialGroup()
                                .addGap(370, 370, 370)
                                .addGroup(jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel11)
                                    .addComponent(c_login_user, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c_login_password, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(304, 304, 304))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_LoginLayout.createSequentialGroup()
                        .addComponent(btn_login_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(351, 351, 351)))
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_LoginLayout.setVerticalGroup(
            jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_LoginLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_LoginLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_login_user, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_login_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_login_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        jTabbedPane1.addTab("Login", jPanel_Login);

        tbl_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Sexo", "CPF", "E-mail", "Senha", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_usuarios.getTableHeader().setReorderingAllowed(false);
        tbl_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usuariosMouseClicked(evt);
            }
        });
        ScrollPane_Usuarios.setViewportView(tbl_usuarios);
        if (tbl_usuarios.getColumnModel().getColumnCount() > 0) {
            tbl_usuarios.getColumnModel().getColumn(0).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_usuarios.getColumnModel().getColumn(1).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbl_usuarios.getColumnModel().getColumn(2).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(3).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(4).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(5).setResizable(false);
            tbl_usuarios.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel_Movimentacoes_Usuarios.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuários"));

        label_codigo.setText("Código:");

        label_nome.setText("Nome:");

        label_sexo.setText("Sexo:");

        label_cpf.setText("CPF:");

        label_email.setText("E-mail:");

        label_senha.setText("Senha:");

        label_acesso.setText("Acesso:");

        c_usuario_cod.setEnabled(false);
        c_usuario_cod.setBackground(new java.awt.Color(204, 204, 204));
        c_usuario_cod.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

        c_usuario_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_usuario_nomeActionPerformed(evt);
            }
        });

        c_usuario_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_usuario_emailActionPerformed(evt);
            }
        });

        btn_usuario_salvar.setText("Salvar");
        btn_usuario_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario_salvarActionPerformed(evt);
            }
        });

        btn_usuario_cancelar.setText("Cancelar");
        btn_usuario_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario_cancelarActionPerformed(evt);
            }
        });

        try {
            c_usuario_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        c_usuario_senha.setText("");

        cb_usuario_tipoAcesso.setModel(new javax.swing.DefaultComboBoxModel<>(ETipoUsuario.obterOpcoesTipoUsuario().toArray(new String[0])));

        cb_usuario_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(ETipoSexo.obterOpcoesSexo().toArray(new String[0])));
        cb_usuario_sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_usuario_sexoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Movimentacoes_UsuariosLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Usuarios);
        jPanel_Movimentacoes_Usuarios.setLayout(jPanel_Movimentacoes_UsuariosLayout);
        jPanel_Movimentacoes_UsuariosLayout.setHorizontalGroup(
            jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_sexo)
                            .addComponent(label_nome)
                            .addComponent(label_codigo))
                        .addComponent(label_cpf, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(label_email)
                    .addComponent(label_senha)
                    .addComponent(label_acesso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_usuario_cpf)
                    .addComponent(c_usuario_cod)
                    .addComponent(c_usuario_nome, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(c_usuario_email, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(c_usuario_senha)
                    .addComponent(cb_usuario_sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_usuario_tipoAcesso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_Movimentacoes_UsuariosLayout.createSequentialGroup()
                        .addComponent(btn_usuario_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_usuario_cancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_Movimentacoes_UsuariosLayout.setVerticalGroup(
            jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_codigo)
                    .addComponent(c_usuario_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_nome)
                    .addComponent(c_usuario_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_sexo)
                    .addComponent(cb_usuario_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_cpf)
                    .addComponent(c_usuario_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_usuario_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_email))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_senha)
                    .addComponent(c_usuario_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_acesso)
                    .addComponent(cb_usuario_tipoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel_Movimentacoes_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_usuario_salvar)
                    .addComponent(btn_usuario_cancelar))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btn_usuario_novo.setText("Novo");
        btn_usuario_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario_novoActionPerformed(evt);
            }
        });

        btn_usuario_editar.setText("Editar");
        btn_usuario_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario_editarActionPerformed(evt);
            }
        });

        btn_usuario_excluir.setText("Excluir");
        btn_usuario_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_UsuariosLayout = new javax.swing.GroupLayout(jPanel_Usuarios);
        jPanel_Usuarios.setLayout(jPanel_UsuariosLayout);
        jPanel_UsuariosLayout.setHorizontalGroup(
            jPanel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane_Usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jPanel_Movimentacoes_Usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_UsuariosLayout.createSequentialGroup()
                        .addComponent(btn_usuario_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_usuario_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_usuario_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_UsuariosLayout.setVerticalGroup(
            jPanel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_usuario_novo)
                    .addComponent(btn_usuario_editar)
                    .addComponent(btn_usuario_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Movimentacoes_Usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel_Movimentacoes_Usuarios.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("Usuarios", jPanel_Usuarios);

        tbl_raca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Espécie", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_raca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_racaMouseClicked(evt);
            }
        });
        ScrollPane_Racas.setViewportView(tbl_raca);
        if (tbl_raca.getColumnModel().getColumnCount() > 0) {
            tbl_raca.getColumnModel().getColumn(0).setResizable(false);
            tbl_raca.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_raca.getColumnModel().getColumn(1).setResizable(false);
            tbl_raca.getColumnModel().getColumn(2).setResizable(false);
            tbl_raca.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jPanel_Movimentacoes_Racas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel6.setText("Código:");

        jLabel7.setText("Nome:");

        c_raca_cod.setBackground(new java.awt.Color(204, 204, 204));

        btn_raca_salvar.setText("Salvar");
        btn_raca_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_raca_salvarActionPerformed(evt);
            }
        });

        btn_raca_cancelar.setText("Cancelar");
        btn_raca_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_raca_cancelarActionPerformed(evt);
            }
        });

        cb_tipoAnimal1.setModel(new javax.swing.DefaultComboBoxModel<>(ETipoAnimal.obterOpcoesTipoAnimal().toArray(new String[0])));
        cb_tipoAnimal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cb_tipoAnimal1MousePressed(evt);
            }
        });
        cb_tipoAnimal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipoAnimal1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Espécie");

        javax.swing.GroupLayout jPanel_Movimentacoes_RacasLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Racas);
        jPanel_Movimentacoes_Racas.setLayout(jPanel_Movimentacoes_RacasLayout);
        jPanel_Movimentacoes_RacasLayout.setHorizontalGroup(
            jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_RacasLayout.createSequentialGroup()
                .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Movimentacoes_RacasLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btn_raca_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_raca_cancelar))
                    .addGroup(jPanel_Movimentacoes_RacasLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(c_raca_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .addComponent(cb_tipoAnimal1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_raca_cod))))
                .addGap(0, 442, Short.MAX_VALUE))
        );
        jPanel_Movimentacoes_RacasLayout.setVerticalGroup(
            jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_RacasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c_raca_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_tipoAnimal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(c_raca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel_Movimentacoes_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_raca_salvar)
                    .addComponent(btn_raca_cancelar))
                .addContainerGap())
        );

        btn_raca_novo.setText("Novo");
        btn_raca_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_raca_novoActionPerformed(evt);
            }
        });

        btn_raca_editar.setText("Editar");
        btn_raca_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_raca_editarActionPerformed(evt);
            }
        });

        btn_raca_excluir.setText("Excluir");
        btn_raca_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_raca_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_RacasLayout = new javax.swing.GroupLayout(jPanel_Racas);
        jPanel_Racas.setLayout(jPanel_RacasLayout);
        jPanel_RacasLayout.setHorizontalGroup(
            jPanel_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPane_Racas, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
            .addGroup(jPanel_RacasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btn_raca_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_raca_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_raca_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel_Movimentacoes_Racas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_RacasLayout.setVerticalGroup(
            jPanel_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RacasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane_Racas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_RacasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_raca_novo)
                    .addComponent(btn_raca_editar)
                    .addComponent(btn_raca_excluir))
                .addGap(18, 18, 18)
                .addComponent(jPanel_Movimentacoes_Racas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Raças", jPanel_Racas);

        tbl_animais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Sexo", "Idade", "Peso", "Raça", "Dono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_animais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_animaisMouseClicked(evt);
            }
        });
        ScrollPane_Animais.setViewportView(tbl_animais);
        if (tbl_animais.getColumnModel().getColumnCount() > 0) {
            tbl_animais.getColumnModel().getColumn(0).setResizable(false);
            tbl_animais.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_animais.getColumnModel().getColumn(1).setResizable(false);
            tbl_animais.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbl_animais.getColumnModel().getColumn(2).setResizable(false);
            tbl_animais.getColumnModel().getColumn(3).setResizable(false);
            tbl_animais.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbl_animais.getColumnModel().getColumn(4).setResizable(false);
            tbl_animais.getColumnModel().getColumn(5).setResizable(false);
            tbl_animais.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel_Movimentacoes_Animais.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Funcionário"));

        jLabel3.setText("Código:");

        c_animal_cod.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("Nome:");

        btn_animal_salvar.setText("Salvar");
        btn_animal_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animal_salvarActionPerformed(evt);
            }
        });

        btn_animal_cancelar.setText("Cancelar");
        btn_animal_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animal_cancelarActionPerformed(evt);
            }
        });

        jLabel5.setText("Raça:");

        jLabel12.setText("Idade:");

        jLabel25.setText("Peso:");

        jLabel26.setText("CPF do Dono:");

        c_animal_idade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_animal_idadeActionPerformed(evt);
            }
        });

        c_animal_peso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_animal_pesoActionPerformed(evt);
            }
        });

        jLabel24.setText("Sexo:");

        cb_tipoSexo2.setModel(new javax.swing.DefaultComboBoxModel<>(ETipoSexo.obterOpcoesSexo().toArray(new String[0])));
        cb_tipoSexo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cb_tipoSexo2MousePressed(evt);
            }
        });
        cb_tipoSexo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipoSexo2ActionPerformed(evt);
            }
        });

        try {
            c_animal_cpfDono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        DefaultComboBoxModel<Raca> comboBoxModel2 = new DefaultComboBoxModel<>();

        List<Raca> racas2 = RacaDao.buscarTodos();
        for (Raca raca2 : racas2) {
            comboBoxModel2.addElement(raca2);
        }
        cb_idRaca3.setModel(comboBoxModel2);
        model.view.RacaComboBoxRenderer.configurarComboBox(cb_idRaca3);
        cb_idRaca3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_idRaca3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Movimentacoes_AnimaisLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Animais);
        jPanel_Movimentacoes_Animais.setLayout(jPanel_Movimentacoes_AnimaisLayout);
        jPanel_Movimentacoes_AnimaisLayout.setHorizontalGroup(
            jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                        .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel25)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_animal_peso)
                            .addComponent(cb_idRaca3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_animal_idade)
                            .addComponent(cb_tipoSexo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_animal_nome)
                            .addComponent(c_animal_cod)))
                    .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_animal_cpfDono)))
                .addContainerGap())
            .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btn_animal_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_animal_cancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Movimentacoes_AnimaisLayout.setVerticalGroup(
            jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_AnimaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(c_animal_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(c_animal_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cb_tipoSexo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(c_animal_idade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_animal_peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cb_idRaca3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(c_animal_cpfDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Movimentacoes_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_animal_salvar)
                    .addComponent(btn_animal_cancelar))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        btn_animal_editar.setText("Editar");
        btn_animal_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animal_editarActionPerformed(evt);
            }
        });

        btn_animal_excluir.setText("Excluir");
        btn_animal_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animal_excluirActionPerformed(evt);
            }
        });

        btn_animal_novo.setText("Novo");
        btn_animal_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animal_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_AnimaisLayout = new javax.swing.GroupLayout(jPanel_Animais);
        jPanel_Animais.setLayout(jPanel_AnimaisLayout);
        jPanel_AnimaisLayout.setHorizontalGroup(
            jPanel_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AnimaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Movimentacoes_Animais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPane_Animais, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel_AnimaisLayout.createSequentialGroup()
                        .addComponent(btn_animal_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_animal_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_animal_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 539, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_AnimaisLayout.setVerticalGroup(
            jPanel_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AnimaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane_Animais, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel_AnimaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_animal_novo)
                    .addComponent(btn_animal_editar)
                    .addComponent(btn_animal_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_Movimentacoes_Animais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Animais", jPanel_Animais);

        tbl_condicoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Raça", "Alergias", "Problemas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_condicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_condicoesMouseClicked(evt);
            }
        });
        ScrollPane_Condicoes.setViewportView(tbl_condicoes);
        if (tbl_condicoes.getColumnModel().getColumnCount() > 0) {
            tbl_condicoes.getColumnModel().getColumn(0).setResizable(false);
            tbl_condicoes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_condicoes.getColumnModel().getColumn(1).setResizable(false);
            tbl_condicoes.getColumnModel().getColumn(2).setResizable(false);
            tbl_condicoes.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbl_condicoes.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel_Movimentacoes_Condicoes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel9.setText("Código:");

        c_condicao_cod.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setText("Alergias:");

        btn_condicaoEspecial_salvar.setText("Salvar");
        btn_condicaoEspecial_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_condicaoEspecial_salvarActionPerformed(evt);
            }
        });

        btn_condicaoEspecial_cancelar.setText("Cancelar");
        btn_condicaoEspecial_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_condicaoEspecial_cancelarActionPerformed(evt);
            }
        });

        c_condicao_prob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_condicao_probActionPerformed(evt);
            }
        });

        jLabel13.setText("Problemas:");

        jLabel14.setText("Raça:");

        DefaultComboBoxModel<Raca> comboBoxModel = new DefaultComboBoxModel<>();

        List<Raca> racas = RacaDao.buscarTodos();
        for (Raca raca : racas) {
            comboBoxModel.addElement(raca);
        }
        cb_idRaca2.setModel(comboBoxModel);
        model.view.RacaComboBoxRenderer.configurarComboBox(cb_idRaca2);
        cb_idRaca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_idRaca2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Movimentacoes_CondicoesLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Condicoes);
        jPanel_Movimentacoes_Condicoes.setLayout(jPanel_Movimentacoes_CondicoesLayout);
        jPanel_Movimentacoes_CondicoesLayout.setHorizontalGroup(
            jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btn_condicaoEspecial_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_condicaoEspecial_cancelar)
                        .addGap(0, 628, Short.MAX_VALUE))
                    .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                        .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_idRaca2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_condicao_alergia)
                            .addComponent(c_condicao_cod)))
                    .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_condicao_prob)))
                .addGap(19, 19, 19))
        );
        jPanel_Movimentacoes_CondicoesLayout.setVerticalGroup(
            jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_CondicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(c_condicao_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cb_idRaca2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_condicao_alergia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_condicao_prob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Movimentacoes_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_condicaoEspecial_salvar)
                    .addComponent(btn_condicaoEspecial_cancelar))
                .addContainerGap())
        );

        btn_condicaoEspecial_novo.setText("Novo");
        btn_condicaoEspecial_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_condicaoEspecial_novoActionPerformed(evt);
            }
        });

        btn_condicaoEspecial_editar.setText("Editar");
        btn_condicaoEspecial_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_condicaoEspecial_editarActionPerformed(evt);
            }
        });

        btn_condicaoEspecial_excluir.setText("Excluir");
        btn_condicaoEspecial_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_condicaoEspecial_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CondicoesLayout = new javax.swing.GroupLayout(jPanel_Condicoes);
        jPanel_Condicoes.setLayout(jPanel_CondicoesLayout);
        jPanel_CondicoesLayout.setHorizontalGroup(
            jPanel_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CondicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_condicaoEspecial_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btn_condicaoEspecial_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_condicaoEspecial_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CondicoesLayout.createSequentialGroup()
                .addGroup(jPanel_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel_Movimentacoes_Condicoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPane_Condicoes, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_CondicoesLayout.setVerticalGroup(
            jPanel_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CondicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane_Condicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CondicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_condicaoEspecial_novo)
                    .addComponent(btn_condicaoEspecial_editar)
                    .addComponent(btn_condicaoEspecial_excluir))
                .addGap(26, 26, 26)
                .addComponent(jPanel_Movimentacoes_Condicoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Condições Especiais", jPanel_Condicoes);

        tbl_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Exames", "Animal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_consultaMouseClicked(evt);
            }
        });
        ScrollPane_Consulta.setViewportView(tbl_consulta);
        if (tbl_consulta.getColumnModel().getColumnCount() > 0) {
            tbl_consulta.getColumnModel().getColumn(0).setResizable(false);
            tbl_consulta.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_consulta.getColumnModel().getColumn(1).setResizable(false);
            tbl_consulta.getColumnModel().getColumn(2).setResizable(false);
            tbl_consulta.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbl_consulta.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel_Movimentacoes_Consulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel29.setText("Código:");

        c_consulta_cod.setBackground(new java.awt.Color(204, 204, 204));

        jLabel30.setText("Data:");

        btn_consulta_salvar.setText("Salvar");
        btn_consulta_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consulta_salvarActionPerformed(evt);
            }
        });

        btn_consulta_cancelar.setText("Cancelar");
        btn_consulta_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consulta_cancelarActionPerformed(evt);
            }
        });

        jLabel31.setText("Animal:");

        jLabel32.setText("Exames:");

        try {
            c_consulta_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        c_consulta_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_consulta_dataActionPerformed(evt);
            }
        });

        DefaultComboBoxModel<Animal> comboBoxModel3 = new DefaultComboBoxModel<>();

        List<Animal> animais = AnimalDao.buscarTodos();
        for (Animal animal : animais) {
            comboBoxModel3.addElement(animal);
        }
        cb_consulta_animal.setModel(comboBoxModel3);
        model.view.AnimalComboBoxRenderer.configurarComboBox(cb_consulta_animal);
        cb_consulta_animal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_consulta_animalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Movimentacoes_ConsultaLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Consulta);
        jPanel_Movimentacoes_Consulta.setLayout(jPanel_Movimentacoes_ConsultaLayout);
        jPanel_Movimentacoes_ConsultaLayout.setHorizontalGroup(
            jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_ConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_Movimentacoes_ConsultaLayout.createSequentialGroup()
                        .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel30)
                                .addComponent(jLabel32)
                                .addComponent(jLabel29)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_consulta_data)
                            .addComponent(c_consulta_cod, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addComponent(c_consulta_exames, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addComponent(cb_consulta_animal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_Movimentacoes_ConsultaLayout.createSequentialGroup()
                        .addComponent(btn_consulta_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_consulta_cancelar)
                        .addGap(0, 665, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel_Movimentacoes_ConsultaLayout.setVerticalGroup(
            jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_ConsultaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(c_consulta_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(c_consulta_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(c_consulta_exames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_consulta_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel_Movimentacoes_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_consulta_salvar)
                    .addComponent(btn_consulta_cancelar))
                .addGap(33, 33, 33))
        );

        btn_consulta_novo.setText("Novo");
        btn_consulta_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consulta_novoActionPerformed(evt);
            }
        });

        btn_consulta_editar.setText("Editar");
        btn_consulta_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consulta_editarActionPerformed(evt);
            }
        });

        btn_consulta_excluir.setText("Excluir");
        btn_consulta_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consulta_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ConsultaLayout = new javax.swing.GroupLayout(jPanel_Consulta);
        jPanel_Consulta.setLayout(jPanel_ConsultaLayout);
        jPanel_ConsultaLayout.setHorizontalGroup(
            jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane_Consulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(jPanel_ConsultaLayout.createSequentialGroup()
                        .addComponent(btn_consulta_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_consulta_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_consulta_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ConsultaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Movimentacoes_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel_ConsultaLayout.setVerticalGroup(
            jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ConsultaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ScrollPane_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_consulta_novo)
                    .addComponent(btn_consulta_editar)
                    .addComponent(btn_consulta_excluir))
                .addContainerGap(321, Short.MAX_VALUE))
            .addGroup(jPanel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ConsultaLayout.createSequentialGroup()
                    .addGap(190, 190, 190)
                    .addComponent(jPanel_Movimentacoes_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Consulta", jPanel_Consulta);

        tbl_tratamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Consulta", "Medicamentos", "Procedimentos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_tratamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tratamentoMouseClicked(evt);
            }
        });
        ScrollPane_Tratamento.setViewportView(tbl_tratamento);
        if (tbl_tratamento.getColumnModel().getColumnCount() > 0) {
            tbl_tratamento.getColumnModel().getColumn(0).setResizable(false);
            tbl_tratamento.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_tratamento.getColumnModel().getColumn(1).setResizable(false);
            tbl_tratamento.getColumnModel().getColumn(2).setResizable(false);
            tbl_tratamento.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbl_tratamento.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel_Movimentacoes_Tratamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel15.setText("Código:");

        c_tratamento_cod.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setText("Consulta:");

        btn_tratamento_salvar.setText("Salvar");
        btn_tratamento_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tratamento_salvarActionPerformed(evt);
            }
        });

        btn_tratamento_cancelar.setText("Cancelar");
        btn_tratamento_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tratamento_cancelarActionPerformed(evt);
            }
        });

        c_tratamento_procedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_tratamento_procedimentoActionPerformed(evt);
            }
        });

        jLabel17.setText("Procedimentos:");

        jLabel18.setText("Medicamentos:");

        ConsultaComboBoxRenderer.configurarComboBox(cb_tratamento_idConsulta);
        DefaultComboBoxModel<Consulta> comboBoxModelConsulta = new DefaultComboBoxModel<>();
        List<Consulta> consultas = ConsultaDao.buscarTodos();

        for (Consulta consulta : consultas) {
            comboBoxModelConsulta.addElement(consulta);
        }

        cb_tratamento_idConsulta.setModel(comboBoxModelConsulta);
        cb_tratamento_idConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tratamento_idConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Movimentacoes_TratamentoLayout = new javax.swing.GroupLayout(jPanel_Movimentacoes_Tratamento);
        jPanel_Movimentacoes_Tratamento.setLayout(jPanel_Movimentacoes_TratamentoLayout);
        jPanel_Movimentacoes_TratamentoLayout.setHorizontalGroup(
            jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_TratamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Movimentacoes_TratamentoLayout.createSequentialGroup()
                        .addComponent(btn_tratamento_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tratamento_cancelar)
                        .addContainerGap(688, Short.MAX_VALUE))
                    .addGroup(jPanel_Movimentacoes_TratamentoLayout.createSequentialGroup()
                        .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel_Movimentacoes_TratamentoLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel15))
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_tratamento_cod, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                            .addComponent(c_tratamento_medicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                            .addComponent(c_tratamento_procedimento)
                            .addComponent(cb_tratamento_idConsulta, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel_Movimentacoes_TratamentoLayout.setVerticalGroup(
            jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Movimentacoes_TratamentoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(c_tratamento_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cb_tratamento_idConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_tratamento_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_tratamento_procedimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel_Movimentacoes_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tratamento_salvar)
                    .addComponent(btn_tratamento_cancelar))
                .addGap(33, 33, 33))
        );

        btn_tratamento_novo.setText("Novo");
        btn_tratamento_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tratamento_novoActionPerformed(evt);
            }
        });

        btn_tratamento_editar.setText("Editar");
        btn_tratamento_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tratamento_editarActionPerformed(evt);
            }
        });

        btn_tratamento_excluir.setText("Excluir");
        btn_tratamento_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tratamento_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_TratamentoLayout = new javax.swing.GroupLayout(jPanel_Tratamento);
        jPanel_Tratamento.setLayout(jPanel_TratamentoLayout);
        jPanel_TratamentoLayout.setHorizontalGroup(
            jPanel_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TratamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_TratamentoLayout.createSequentialGroup()
                        .addComponent(btn_tratamento_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tratamento_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tratamento_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel_Movimentacoes_Tratamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPane_Tratamento, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_TratamentoLayout.setVerticalGroup(
            jPanel_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TratamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane_Tratamento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_TratamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tratamento_novo)
                    .addComponent(btn_tratamento_editar)
                    .addComponent(btn_tratamento_excluir))
                .addGap(28, 28, 28)
                .addComponent(jPanel_Movimentacoes_Tratamento, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tratamento", jPanel_Tratamento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_idRaca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_idRaca2ActionPerformed

    }//GEN-LAST:event_cb_idRaca2ActionPerformed

    private void c_condicao_probActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_condicao_probActionPerformed

    }//GEN-LAST:event_c_condicao_probActionPerformed

    private void btn_condicaoEspecial_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_condicaoEspecial_cancelarActionPerformed
        LimparCampos();
    }//GEN-LAST:event_btn_condicaoEspecial_cancelarActionPerformed

    private void btn_condicaoEspecial_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_condicaoEspecial_salvarActionPerformed
        PreparedStatement ps2 = null;
        
        int cod = Integer.parseInt(c_condicao_cod.getText());   
        
        Raca racaSelecionada = (Raca) cb_idRaca2.getSelectedItem();
        int idRaca = racaSelecionada.getId(); 

        switch (modoCondicoesEspeciais) {
            case "Novo" -> {
                CondicoesEspeciais condicoes = new CondicoesEspeciais(cod, racaSelecionada, c_condicao_alergia.getText(), c_condicao_prob.getText());
                CondicoesEspeciaisDao.inserir(condicoes);
                ListaCondicoesEspeciais.add(condicoes);
            }
            case "Editar" -> {
                int index = tbl_condicoes.getSelectedRow();

                if (index != -1) {
                    CondicoesEspeciais condicoes = ListaCondicoesEspeciais.get(index);
                    condicoes.setAlergias(c_condicao_alergia.getText());
                    condicoes.setProblemasDeSaude(c_condicao_prob.getText());
                    CondicoesEspeciaisDao.alterar(condicoes);                  
                }
            }
        }
        
        LoadTableCondicoesEspeciais();
        LimparCampos();
    }//GEN-LAST:event_btn_condicaoEspecial_salvarActionPerformed

    private void btn_condicaoEspecial_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_condicaoEspecial_excluirActionPerformed
        int index = tbl_condicoes.getSelectedRow();
        
        if (index != -1) { 
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir as condições especiais?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_condicoes.getValueAt(index, 0);
                CondicoesEspeciaisDao.apagar(id);
                ListaCondicoesEspeciais.remove(index);
                LoadTableCondicoesEspeciais();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione condições especiais para excluir.");
        }
    }//GEN-LAST:event_btn_condicaoEspecial_excluirActionPerformed

    private void btn_condicaoEspecial_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_condicaoEspecial_editarActionPerformed
        int selectedRow = tbl_condicoes.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione condições especiais para editar.");
            return;
        }

        int id = (int) tbl_condicoes.getValueAt(selectedRow, 0);
        
        Raca racaSelecionada = (Raca) cb_idRaca2.getSelectedItem();
        int idRaca = racaSelecionada.getId();
        
        String alergias = (String) tbl_condicoes.getValueAt(selectedRow, 2);
        String problemasDeSaude = (String) tbl_condicoes.getValueAt(selectedRow, 3);

        Raca raca = RacaDao.buscarPelaChave(idRaca);

        c_condicao_cod.setText(String.valueOf(id));
        cb_idRaca2.setSelectedItem(raca.getNome());
        c_condicao_alergia.setText(alergias);
        c_condicao_prob.setText(problemasDeSaude);
        
        modoCondicoesEspeciais = "Editar";
    }//GEN-LAST:event_btn_condicaoEspecial_editarActionPerformed

    private void btn_condicaoEspecial_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_condicaoEspecial_novoActionPerformed
        LimparCampos();
        
        int proximoCodigo = CondicoesEspeciaisDao.obterProximoCodigo();
        c_condicao_cod.setText(String.valueOf(proximoCodigo));
        
        modoCondicoesEspeciais = "Novo";
    }//GEN-LAST:event_btn_condicaoEspecial_novoActionPerformed

    private void tbl_condicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_condicoesMouseClicked

    }//GEN-LAST:event_tbl_condicoesMouseClicked

    private void btn_raca_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_raca_cancelarActionPerformed
        LimparCampos();
    }//GEN-LAST:event_btn_raca_cancelarActionPerformed

    private void btn_raca_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_raca_salvarActionPerformed
        PreparedStatement ps2 = null;

        int cod = Integer.parseInt(c_raca_cod.getText());   
        ETipoAnimal tipoAnimal = ETipoAnimal.valueOf(cb_tipoAnimal1.getSelectedItem().toString());
          
        switch (modoRaca) {
            case "Novo" -> {
               Raca raca = new Raca(cod, tipoAnimal, c_raca_nome.getText());
               RacaDao.inserir(raca);
               ListaRaca.add(raca); 
            }
            
            case "Editar" -> {
                 int index = tbl_raca.getSelectedRow();

                 if (index != -1) {
                     Raca raca = ListaRaca.get(index);
                     raca.setNome(c_raca_nome.getText());
                     raca.setTipoAnimal(tipoAnimal);
                     RacaDao.alterar(raca);
                 }
            }
        }

        LoadTableRaca();
        LimparCampos();
    }//GEN-LAST:event_btn_raca_salvarActionPerformed
       
    private void btn_raca_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_raca_excluirActionPerformed
        int index = tbl_raca.getSelectedRow();
        if (index != -1) { 
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir o usuário?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_raca.getValueAt(index, 0);
                RacaDao.apagar(id);
                ListaRaca.remove(index);
                LoadTableRaca();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um usuário para excluir.");
        }     
    }//GEN-LAST:event_btn_raca_excluirActionPerformed

    private void btn_raca_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_raca_editarActionPerformed
        int selectedRow = tbl_raca.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um usuário para editar.");
            return;
        }

        int id = (int) tbl_raca.getValueAt(selectedRow, 0);
        String tipoAnimal = (String) tbl_raca.getValueAt(selectedRow, 1); 
        String nome = (String) tbl_raca.getValueAt(selectedRow, 2); 
        
        c_raca_cod.setText(String.valueOf(id));
        cb_tipoAnimal1.setSelectedItem(tipoAnimal);
        c_raca_nome.setText(nome);
        
        modoRaca = "Editar";
    }//GEN-LAST:event_btn_raca_editarActionPerformed

    private void btn_raca_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_raca_novoActionPerformed
        LimparCampos();

        int proximoCodigo = RacaDao.obterProximoCodigo();
        c_raca_cod.setText(String.valueOf(proximoCodigo));
        
        modoRaca = "Novo";
    }//GEN-LAST:event_btn_raca_novoActionPerformed

    private void tbl_racaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_racaMouseClicked

    }//GEN-LAST:event_tbl_racaMouseClicked

    private void btn_animal_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animal_novoActionPerformed
        LimparCampos();
        
        int proximoCodigo = AnimalDao.obterProximoCodigo();
        c_animal_cod.setText(String.valueOf(proximoCodigo));
        
        modoAnimal = "Novo";
    }//GEN-LAST:event_btn_animal_novoActionPerformed

    private void btn_animal_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animal_cancelarActionPerformed
        LimparCampos();
    }//GEN-LAST:event_btn_animal_cancelarActionPerformed

    private void btn_animal_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animal_salvarActionPerformed
        PreparedStatement ps2 = null;

        int cod = Integer.parseInt(c_animal_cod.getText());

        ETipoSexo sexo = ETipoSexo.valueOf(cb_tipoSexo2.getSelectedItem().toString());

        Raca racaSelecionada = (Raca) cb_idRaca2.getSelectedItem();
        int idRaca = racaSelecionada.getId();

        switch (modoAnimal) {
            case "Novo" -> {
                Animal animal = new Animal(cod, c_animal_nome.getText(), sexo, Integer.parseInt(c_animal_idade.getText()), Integer.parseInt(c_animal_peso.getText()), racaSelecionada, c_animal_cpfDono.getText());
                AnimalDao.inserir(animal);
                ListaAnimal.add(animal);
                LoadTableAnimal();
            }
            case "Editar" -> {
                int index = tbl_animais.getSelectedRow();

                if (index != -1) {
                    Animal animal = ListaAnimal.get(index);
                    animal.setNome(c_animal_nome.getText());
                    animal.setSexo(sexo);
                    animal.setIdade(Integer.parseInt(c_animal_idade.getText()));
                    animal.setPeso(Integer.parseInt(c_animal_peso.getText()));
                    animal.setRaca(racaSelecionada);
                    animal.setCpfDono(c_animal_cpfDono.getText());
                    AnimalDao.alterar(animal);
                    LoadTableAnimal();
                }
            }
        }
        LimparCampos();
    }//GEN-LAST:event_btn_animal_salvarActionPerformed

    private void btn_animal_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animal_excluirActionPerformed
        int index = tbl_animais.getSelectedRow();
        
        if (index != -1) { 
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse animal?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_animais.getValueAt(index, 0);
                AnimalDao.apagar(id);
                ListaAnimal.remove(index);
                LoadTableAnimal();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um animal para excluir.");
        }
    }//GEN-LAST:event_btn_animal_excluirActionPerformed

    private void tbl_animaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_animaisMouseClicked

    }//GEN-LAST:event_tbl_animaisMouseClicked

    private void btn_animal_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animal_editarActionPerformed
        int selectedRow = tbl_animais.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um animal para editar.");
            return;
        }
        
        int id = (int) tbl_animais.getValueAt(selectedRow, 0);
        String nome = (String) tbl_animais.getValueAt(selectedRow, 1);
        ETipoSexo sexo = (ETipoSexo) tbl_animais.getValueAt(selectedRow, 2);
        int idade = (int) tbl_animais.getValueAt(selectedRow, 3);
        int peso = (int) tbl_animais.getValueAt(selectedRow, 4);
        String tipoAnimal = (String) tbl_animais.getValueAt(selectedRow, 5); 
        String cpfDono = (String) tbl_animais.getValueAt(selectedRow, 6);
        
        c_animal_cod.setText(String.valueOf(id));
        c_animal_nome.setText(nome);
        cb_tipoSexo2.setSelectedItem(sexo);
        c_animal_idade.setText(String.valueOf(idade));
        c_animal_peso.setText(String.valueOf(peso));     
        cb_idRaca3.setSelectedItem(tipoAnimal);
        c_animal_cpfDono.setText(String.valueOf(cpfDono));
        
        modoAnimal = "Editar";
    }//GEN-LAST:event_btn_animal_editarActionPerformed

    private void btn_usuario_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario_excluirActionPerformed
        int index = tbl_usuarios.getSelectedRow();
        if (index != -1) { // Verifica se alguma linha está selecionada na tabela
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir o usuário?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_usuarios.getValueAt(index, 0);
                UsuarioDao.apagar(id);
                ListaUsuario.remove(index);
                LoadTableUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um usuário para excluir.");
        }
    }//GEN-LAST:event_btn_usuario_excluirActionPerformed

    private void btn_usuario_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario_editarActionPerformed
        int selectedRow = tbl_usuarios.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um usuário para editar.");
            return;
        }

        int id = (int) tbl_usuarios.getValueAt(selectedRow, 0);
        String nome = (String) tbl_usuarios.getValueAt(selectedRow, 1); 
        String sexo = (String) tbl_usuarios.getValueAt(selectedRow, 2); 
        String cpf = (String) tbl_usuarios.getValueAt(selectedRow, 3); 
        String email = (String) tbl_usuarios.getValueAt(selectedRow, 4);
        String senha = (String) tbl_usuarios.getValueAt(selectedRow, 5); 
        String tipoUsuario = (String) tbl_usuarios.getValueAt(selectedRow, 6); 

        c_usuario_cod.setText(String.valueOf(id));
        c_usuario_nome.setText(nome);
        c_usuario_cpf.setText(cpf);
        c_usuario_email.setText(email);
        c_usuario_senha.setText(senha);
        cb_usuario_sexo.setSelectedItem(sexo);
        cb_usuario_tipoAcesso.setSelectedItem(tipoUsuario);
        
        modoUsuario = "editar";
    }//GEN-LAST:event_btn_usuario_editarActionPerformed

    private void btn_usuario_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario_novoActionPerformed
        LimparCampos();
        
        int proximoCodigo = UsuarioDao.obterProximoCodigo();
        c_usuario_cod.setText(String.valueOf(proximoCodigo));
        
        modoUsuario = "Novo";
    }//GEN-LAST:event_btn_usuario_novoActionPerformed

    private void btn_usuario_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario_cancelarActionPerformed
        LimparCampos();
    }//GEN-LAST:event_btn_usuario_cancelarActionPerformed

    private void btn_usuario_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario_salvarActionPerformed
        PreparedStatement ps2 = null;

        char[] senhaChars = c_usuario_senha.getPassword();
        String senha = new String(senhaChars);
   
        int cod = Integer.parseInt(c_usuario_cod.getText());
        
        ETipoSexo sexo = ETipoSexo.valueOf(cb_usuario_sexo.getSelectedItem().toString());
        ETipoUsuario acesso = ETipoUsuario.valueOf(cb_usuario_tipoAcesso.getSelectedItem().toString());
          
        switch (modoUsuario) {
            case "Novo" -> {
               Usuario usuario = new Usuario(cod, c_usuario_nome.getText(), c_usuario_email.getText(), senha, c_usuario_cpf.getText(), acesso, sexo);
               UsuarioDao.inserir(usuario);
               ListaUsuario.add(usuario); 
            }
            case "Editar" -> {
                int index = tbl_usuarios.getSelectedRow();

                if (index != -1) { 
                    Usuario usuario = ListaUsuario.get(index);
                    usuario.setNome(c_usuario_nome.getText());
                    usuario.setEmail(c_usuario_email.getText());
                    usuario.setSenha(senha);
                    usuario.setCpf(c_usuario_cpf.getText());
                    usuario.setTipoUsuario(acesso);
                    usuario.setSexo(sexo);
                    UsuarioDao.alterar(usuario);
                }
            }
        }

        LoadTableUsuarios();
        LimparCampos();
    }//GEN-LAST:event_btn_usuario_salvarActionPerformed

    private void tbl_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usuariosMouseClicked

    }//GEN-LAST:event_tbl_usuariosMouseClicked

    private void c_usuario_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_usuario_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_usuario_nomeActionPerformed

    private void c_usuario_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_usuario_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_usuario_emailActionPerformed

    private void c_animal_idadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_animal_idadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_animal_idadeActionPerformed

    private void c_animal_pesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_animal_pesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_animal_pesoActionPerformed

    private void tbl_tratamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tratamentoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_tratamentoMouseClicked

    private void c_tratamento_procedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_tratamento_procedimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_tratamento_procedimentoActionPerformed

    private void btn_tratamento_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tratamento_cancelarActionPerformed
       LimparCampos();
    }//GEN-LAST:event_btn_tratamento_cancelarActionPerformed

    private void btn_tratamento_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tratamento_salvarActionPerformed
        PreparedStatement ps2 = null;      
        
        int cod = Integer.parseInt(c_tratamento_cod.getText());
        
        Consulta consultaSelecionada = (Consulta) cb_tratamento_idConsulta.getSelectedItem();
        int idConsulta = consultaSelecionada.getId();

        String medicamentos = c_tratamento_medicamento.getText();
        String procedimentos = c_tratamento_procedimento.getText();

        switch (modoTratamento) {
            case "Novo" -> {
                Tratamento tratamentoNovo = new Tratamento(cod, consultaSelecionada, medicamentos, procedimentos);
                TratamentoDao.inserir(tratamentoNovo);
                ListaTratamento.add(tratamentoNovo);
            }
            case "Editar" -> {
                int indexEditar = tbl_tratamento.getSelectedRow();
                if (indexEditar != -1) {
                    Tratamento tratamentoEditar = ListaTratamento.get(indexEditar);
                    tratamentoEditar.setConsulta(consultaSelecionada);
                    tratamentoEditar.setMedicamentos(medicamentos);
                    tratamentoEditar.setProcedimentos(procedimentos);
                    TratamentoDao.alterar(tratamentoEditar);
                }
            }
        }

        LoadTableTratamento();
        LimparCampos();
    }//GEN-LAST:event_btn_tratamento_salvarActionPerformed

    private void btn_tratamento_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tratamento_excluirActionPerformed
       int index = tbl_tratamento.getSelectedRow();
        
        if (index != -1) { 
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse tratamento?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_tratamento.getValueAt(index, 0);
                TratamentoDao.apagar(id);
                ListaTratamento.remove(index);
                LoadTableTratamento();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um tratamento para excluir.");
        }
    }//GEN-LAST:event_btn_tratamento_excluirActionPerformed

    private void btn_tratamento_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tratamento_editarActionPerformed
       int selectedRow = tbl_tratamento.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um tratamento para editar.");
            return;
        }

        int id = (int) tbl_tratamento.getValueAt(selectedRow, 0);   

        Consulta consultaSelecionada = (Consulta) tbl_tratamento.getValueAt(selectedRow, 1); // Assumindo que a coluna 1 contém o objeto Consulta
        int idConsulta = consultaSelecionada.getId();

        String medicamento = (String) tbl_tratamento.getValueAt(selectedRow, 2);
        String procedimento = (String) tbl_tratamento.getValueAt(selectedRow, 3);

        c_tratamento_cod.setText(String.valueOf(id));
        cb_tratamento_idConsulta.setSelectedItem(consultaSelecionada); // Define o objeto Consulta diretamente
        c_tratamento_medicamento.setText(medicamento);
        c_tratamento_procedimento.setText(procedimento);

        modoTratamento = "Editar";

    }//GEN-LAST:event_btn_tratamento_editarActionPerformed

    private void btn_tratamento_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tratamento_novoActionPerformed
        LimparCampos();
        
        int proximoCodigo = CondicoesEspeciaisDao.obterProximoCodigo();
        c_tratamento_cod.setText(String.valueOf(proximoCodigo));
        
        modoTratamento = "Novo";
    }//GEN-LAST:event_btn_tratamento_novoActionPerformed

    private void tbl_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_consultaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_consultaMouseClicked

    private void btn_consulta_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consulta_novoActionPerformed
        LimparCampos();

        int proximoCodigo = ConsultaDao.obterProximoCodigo();
        c_consulta_cod.setText(String.valueOf(proximoCodigo));

        List<Animal> animais = AnimalDao.buscarTodos();
        DefaultComboBoxModel<Animal> comboBoxModel = new DefaultComboBoxModel<>();
        for (Animal animal : animais) {
            comboBoxModel.addElement(animal);
        }
        cb_consulta_animal.setModel(comboBoxModel);
        AnimalComboBoxRenderer.configurarComboBox(cb_consulta_animal);
        
        modoConsulta = "Novo";
    }//GEN-LAST:event_btn_consulta_novoActionPerformed

    private void btn_consulta_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consulta_editarActionPerformed
       int selectedRow = tbl_consulta.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma consulta para editar.");
            return;
        }

        int id = (int) tbl_consulta.getValueAt(selectedRow, 0);

        Animal animalSelecionado = (Animal) cb_consulta_animal.getSelectedItem();
        
        String exames = (String) tbl_consulta.getValueAt(selectedRow, 2);

        java.sql.Date dataConsultaSQL = (java.sql.Date) tbl_consulta.getValueAt(selectedRow, 1);

        Date dataConsulta = new Date(dataConsultaSQL.getTime());

        c_consulta_cod.setText(String.valueOf(id));
        cb_consulta_animal.setSelectedItem(animalSelecionado);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        c_consulta_data.setValue(sdf.format(dataConsulta));

        c_consulta_exames.setText(exames);
        modoConsulta = "Editar";
    }//GEN-LAST:event_btn_consulta_editarActionPerformed

    private void btn_consulta_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consulta_excluirActionPerformed
        int selectedRow = tbl_consulta.getSelectedRow();

        if (selectedRow != -1) {
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir a consulta?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                int id = (int) tbl_consulta.getValueAt(selectedRow, 0);
                ConsultaDao.apagar(id);
                ListaConsulta.remove(selectedRow);
                LoadTableConsulta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma consulta para excluir.");
        }
    }//GEN-LAST:event_btn_consulta_excluirActionPerformed

    private void btn_consulta_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consulta_salvarActionPerformed
        try {
            PreparedStatement ps2 = null;

            int cod = Integer.parseInt(c_consulta_cod.getText());

            String dataConsultaString = c_consulta_data.getText();
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            var dataConsultaUtil = dataFormat.parse(dataConsultaString);

            java.sql.Date dataConsulta = new java.sql.Date(dataConsultaUtil.getTime());

            Animal animalSelecionado = (Animal) cb_consulta_animal.getSelectedItem();

            switch (modoConsulta) {
                case "Novo" -> {
                    Consulta consulta = new Consulta(cod, dataConsulta, c_consulta_exames.getText(), animalSelecionado);
                    ConsultaDao.inserir(consulta);
                    ListaConsulta.add(consulta);
                }
                case "Editar" -> {
                    int index = tbl_consulta.getSelectedRow();

                    if (index != -1) {
                        Consulta consulta = ListaConsulta.get(index);
                        consulta.setData(dataConsulta);
                        consulta.setExames(c_consulta_exames.getText());
                        consulta.setAnimal(animalSelecionado);
                        ConsultaDao.alterar(consulta);
                        
                    }
                }
            }
            LoadTableConsulta();
            LimparCampos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_consulta_salvarActionPerformed

    private void btn_consulta_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consulta_cancelarActionPerformed
        LimparCampos();
    }//GEN-LAST:event_btn_consulta_cancelarActionPerformed

    private void c_consulta_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_consulta_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_consulta_dataActionPerformed

    private void cb_tratamento_idConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tratamento_idConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tratamento_idConsultaActionPerformed

    private void cb_tipoSexo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_tipoSexo2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipoSexo2MousePressed

    private void cb_tipoSexo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipoSexo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipoSexo2ActionPerformed

    private void cb_tipoAnimal1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_tipoAnimal1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipoAnimal1MousePressed

    private void cb_tipoAnimal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipoAnimal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipoAnimal1ActionPerformed

    private void cb_idRaca3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_idRaca3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_idRaca3ActionPerformed

    private void cb_usuario_sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_usuario_sexoActionPerformed

    }//GEN-LAST:event_cb_usuario_sexoActionPerformed

    private void cb_consulta_animalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_consulta_animalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_consulta_animalActionPerformed

    private void c_login_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_login_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_login_userActionPerformed

    private void btn_login_entrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login_entrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login_entrarMouseClicked

    private void btn_login_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login_entrarActionPerformed
        String email = c_login_user.getText();
        String senha = new String(c_login_password.getPassword());
        
        UsuarioDao usuarioDao = new UsuarioDao(); 
        Usuario usuarioAutenticado = usuarioDao.login(email, senha);
        
        if (usuarioAutenticado != null) { 
            /* libera todas as abas, futuramente manipular p/ tipo de usuario */
                jTabbedPane1.setEnabled(true); 
            
            /* carrega os dados gerais das tabelas -> p/ usuario funcionario */
                LoadTableUsuarios();
                LoadTableAnimal();
                LoadTableRaca();
                LoadTableCondicoesEspeciais();
                LoadTableConsulta();
                LoadTableTratamento(); 
            
            /*montar manipulação do tipo de acesso de usuario*/
            if ("Funcionario".equals(usuarioAutenticado.getTipoUsuario())) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido, Funcionário!");                       
            } else {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido, Cliente!");
            }                
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Verifique suas credenciais.");
        }

    }//GEN-LAST:event_btn_login_entrarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane_Animais;
    private javax.swing.JScrollPane ScrollPane_Condicoes;
    private javax.swing.JScrollPane ScrollPane_Consulta;
    private javax.swing.JScrollPane ScrollPane_Racas;
    private javax.swing.JScrollPane ScrollPane_Tratamento;
    private javax.swing.JScrollPane ScrollPane_Usuarios;
    private javax.swing.JButton btn_animal_cancelar;
    private javax.swing.JButton btn_animal_editar;
    private javax.swing.JButton btn_animal_excluir;
    private javax.swing.JButton btn_animal_novo;
    private javax.swing.JButton btn_animal_salvar;
    private javax.swing.JButton btn_condicaoEspecial_cancelar;
    private javax.swing.JButton btn_condicaoEspecial_editar;
    private javax.swing.JButton btn_condicaoEspecial_excluir;
    private javax.swing.JButton btn_condicaoEspecial_novo;
    private javax.swing.JButton btn_condicaoEspecial_salvar;
    private javax.swing.JButton btn_consulta_cancelar;
    private javax.swing.JButton btn_consulta_editar;
    private javax.swing.JButton btn_consulta_excluir;
    private javax.swing.JButton btn_consulta_novo;
    private javax.swing.JButton btn_consulta_salvar;
    private javax.swing.JButton btn_login_entrar;
    private javax.swing.JButton btn_raca_cancelar;
    private javax.swing.JButton btn_raca_editar;
    private javax.swing.JButton btn_raca_excluir;
    private javax.swing.JButton btn_raca_novo;
    private javax.swing.JButton btn_raca_salvar;
    private javax.swing.JButton btn_tratamento_cancelar;
    private javax.swing.JButton btn_tratamento_editar;
    private javax.swing.JButton btn_tratamento_excluir;
    private javax.swing.JButton btn_tratamento_novo;
    private javax.swing.JButton btn_tratamento_salvar;
    private javax.swing.JButton btn_usuario_cancelar;
    private javax.swing.JButton btn_usuario_editar;
    private javax.swing.JButton btn_usuario_excluir;
    private javax.swing.JButton btn_usuario_novo;
    private javax.swing.JButton btn_usuario_salvar;
    private javax.swing.JTextField c_animal_cod;
    private javax.swing.JFormattedTextField c_animal_cpfDono;
    private javax.swing.JTextField c_animal_idade;
    private javax.swing.JTextField c_animal_nome;
    private javax.swing.JTextField c_animal_peso;
    private javax.swing.JTextField c_condicao_alergia;
    private javax.swing.JTextField c_condicao_cod;
    private javax.swing.JTextField c_condicao_prob;
    private javax.swing.JTextField c_consulta_cod;
    private javax.swing.JFormattedTextField c_consulta_data;
    private javax.swing.JTextField c_consulta_exames;
    private javax.swing.JPasswordField c_login_password;
    private javax.swing.JTextField c_login_user;
    private javax.swing.JTextField c_raca_cod;
    private javax.swing.JTextField c_raca_nome;
    private javax.swing.JTextField c_tratamento_cod;
    private javax.swing.JTextField c_tratamento_medicamento;
    private javax.swing.JTextField c_tratamento_procedimento;
    private javax.swing.JTextField c_usuario_cod;
    private javax.swing.JFormattedTextField c_usuario_cpf;
    private javax.swing.JTextField c_usuario_email;
    private javax.swing.JTextField c_usuario_nome;
    private javax.swing.JPasswordField c_usuario_senha;
    private javax.swing.JComboBox cb_consulta_animal;
    private javax.swing.JComboBox cb_idRaca2;
    private javax.swing.JComboBox cb_idRaca3;
    private javax.swing.JComboBox cb_tipoAnimal1;
    private javax.swing.JComboBox<String> cb_tipoSexo2;
    private javax.swing.JComboBox cb_tratamento_idConsulta;
    private javax.swing.JComboBox<String> cb_usuario_sexo;
    private javax.swing.JComboBox<String> cb_usuario_tipoAcesso;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel_Animais;
    private javax.swing.JPanel jPanel_Condicoes;
    private javax.swing.JPanel jPanel_Consulta;
    private javax.swing.JPanel jPanel_Login;
    private javax.swing.JPanel jPanel_Movimentacoes_Animais;
    private javax.swing.JPanel jPanel_Movimentacoes_Condicoes;
    private javax.swing.JPanel jPanel_Movimentacoes_Consulta;
    private javax.swing.JPanel jPanel_Movimentacoes_Racas;
    private javax.swing.JPanel jPanel_Movimentacoes_Tratamento;
    private javax.swing.JPanel jPanel_Movimentacoes_Usuarios;
    private javax.swing.JPanel jPanel_Racas;
    private javax.swing.JPanel jPanel_Tratamento;
    private javax.swing.JPanel jPanel_Usuarios;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label_acesso;
    private javax.swing.JLabel label_codigo;
    private javax.swing.JLabel label_cpf;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_nome;
    private javax.swing.JLabel label_senha;
    private javax.swing.JLabel label_sexo;
    private javax.swing.JTable tbl_animais;
    private javax.swing.JTable tbl_condicoes;
    private javax.swing.JTable tbl_consulta;
    private javax.swing.JTable tbl_raca;
    private javax.swing.JTable tbl_tratamento;
    private javax.swing.JTable tbl_usuarios;
    // End of variables declaration//GEN-END:variables
}




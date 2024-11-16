import oracledb
import pandas as pd
from autenticacao import dados_autenticar

class OperacaoBancoDados:
    def __init__(self, nome_tabela: str, nome_conexao: str):
        self.db_config = dados_autenticar["credencial_banco"]
        self.nome_tabela = nome_tabela
        self.nome_conexao = nome_conexao
        self.conexao = None

    def conectar(self):
        """Método para conectar ao banco de dados Oracle."""
        try:
            self.conexao = oracledb.connect(
                user=self.db_config['usuario'], 
                password=self.db_config['senha'], 
                dsn=self.db_config['dsn'], 
                mode=oracledb.DEFAULT_AUTH
            )
            print("Conexão com o banco de dados Oracle estabelecida com sucesso.")
        except oracledb.DatabaseError as e:
            print(f"Erro ao conectar ao banco de dados: {e}")
            self.conexao = None
        return self.conexao

    def executar_consulta(self, consulta: str):
        """Método para executar uma consulta SQL e retornar os dados em um DataFrame."""
        if self.conexao:
            try:
                df = pd.read_sql(consulta, self.conexao)
                return df
            except Exception as e:
                print(f"Erro ao executar a consulta: {e}")
                return None
        else:
            print("Conexão não estabelecida.")
            return None

    def fechar_conexao(self):
        """Método para fechar a conexão com o banco de dados."""
        if self.conexao:
            self.conexao.close()
            print("Conexão com o banco de dados fechada.")
        else:
            print("Nenhuma conexão aberta para fechar.")

from flask import Flask, request, jsonify
import pandas as pd
import joblib

app = Flask(__name__)

# Carregar o modelo treinado
modelo = joblib.load('modelo_prever_valor.pkl')

# Rota para fazer previsões
@app.route('/prever', methods=['POST'])
def prever():
    try:
        dados_cliente = request.get_json()

        df_cliente = pd.DataFrame(dados_cliente)

        df_encoded = pd.get_dummies(df_cliente, columns=['turno', 'item'], drop_first=True)

        for coluna in modelo.feature_names_in_:
            if coluna not in df_encoded.columns:
                df_encoded[coluna] = 0

        df_encoded = df_encoded[modelo.feature_names_in_]

        previsao = modelo.predict(df_encoded)

        return jsonify({'valor_estimado': round(previsao[0], 2)})

    except Exception as e:
        return jsonify({'erro': str(e)}), 400

if __name__ == '__main__':
    app.run(debug=True)

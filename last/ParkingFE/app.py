# app.py

from flask import Flask, render_template, Response
from flask_socketio import SocketIO, emit
import psycopg2

app = Flask(__name__)
app.config['SECRET_KEY'] = 'AlphaNum!!$aa123546man07'
socketio = SocketIO(app)

# Function to query the database and fetch slot availability
def get_slot_availability():
    conn = psycopg2.connect(
        dbname="ytdemo",
        user="postgres",
        password="1234",
        host="localhost",
        port="5432"
    )
    cur = conn.cursor()
    cur.execute("SELECT slot_number, available FROM parking_data")
    slots = cur.fetchall()
    print(slots)
    conn.close()
    return slots

@app.route('/')
def index():
    slots = get_slot_availability()
    response = Response(render_template('index.html', slots=slots))
    response.headers['Cache-Control'] = 'no-cache, no-store, must-revalidate'
    return response

@socketio.on('update_slots')
def handle_update():
    slots = get_slot_availability()
    emit('slots_updated', slots)
    print("slots_updated!")

if __name__ == '__main__':
    socketio.run(app, debug=True)

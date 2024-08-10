import pymysql

# MySql에 데이터를 입력하는 Script
connect = pymysql.connect(host="[url]", user="[username]", password="[password]", db="[DBname]", charset="utf8")

cur = connect.cursor()
cur.execute("""
    CREATE TABLE weather_table (
        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        year INT NOT NULL,
        month INT NOT NULL,
        day INT NOT NULL,
    
        temp INT NOT NULL,
        temp_max INT NOT NULL,
        temp_min INT NOT NULL,
    
        precipitation INT NOT NULL,
        wind_speed INT NOT NULL,
        humidity INT NOT NULL
    );
""")

import csv
import os
import time

start = time.time()
 
save_path = "files/"

file_list = os.listdir(save_path)
file_list_csv = [file for file in file_list if file.endswith(".csv")]

for file in file_list_csv:
    f = open(save_path + file,'r')
    rdr = csv.reader(f)


    def check(data):
        if(data == ""): return 0
        
        d = data.split(".")
        if(d[0] == ""): return 0
        if(d[0] == "-"): return 0

        return int(d[0])

    def date(data):
        d = data.split("-")
        return {
            "y": d[0],
            "m": d[1],
            "d": d[2]
        }
        
    for line in rdr:
        date_data = line[1]
        if(date_data == "일시"):continue

        날짜 = date(date_data)
        평균온도 = check(line[2])
        최저온도 = check(line[3])
        최고온도 = check(line[5])

        강수량 = check(line[12])
        풍속 = check(line[19])
        습도 = check(line[24])

        날짜String = "{}년 {}월 {}일".format(날짜["y"], 날짜["m"], 날짜["d"])
        print(날짜String + "\t{}\t{}\t{}\t{}\t{}\t{}".format(평균온도, 최저온도, 최고온도, 강수량, 풍속, 습도))

        query = "INSERT INTO weather_table (year, month, day, temp, temp_max, temp_min, precipitation, wind_speed, humidity) VALUES ({}, {}, {}, {}, {}, {}, {}, {}, {});".format(날짜["y"], 날짜["m"], 날짜["d"], 평균온도, 최저온도, 최고온도, 강수량, 풍속, 습도)
        # print(query)
        cur.execute(query)
        connect.commit()
    
    f.close()

end = time.time()
print(f"{end - start:.5f} sec")
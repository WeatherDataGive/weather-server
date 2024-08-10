import zipfile
import os

# 압축 푸는 Script
# 다운로드 받은 파일의 구조에 맞춰서 작성

# 현재 파일이 저장된 위치
file_path = "[filePath]"
# 압축을 풀고 파일을 저장할 위치
save_path = "files/"

def unzip(path, f, savePath):
    with zipfile.ZipFile(path + f, 'r') as zf:
        zipinfo = zf.infolist()
        for info in zipinfo:
            info.filename = savePath + info.filename.encode('cp437').decode('euc-kr')
            zf.extract(info)

file_list = os.listdir(file_path)
file_list_zip = [file for file in file_list if file.endswith(".zip")]
for f in file_list_zip:
    unzip(file_path, f, save_path)

file_list = os.listdir(save_path)
file_list_zip = [file for file in file_list if file.endswith(".zip")]
for f in file_list_zip:
    unzip(save_path, f, save_path)
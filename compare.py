def generate_lists():
    class_list = []
    sign_in_list = []

    # 读取班级名单表格
    with open(r"C:\12\2\data\卓越班-大三-全名单.txt", "r", encoding="utf-8") as f:

        for line in f:
            class_list.append(line.strip())

    # 读取签到表
    with open(r"C:\12\2\data\卓越班-大三-1115.txt", "r", encoding="utf-8") as f:
        next(f)
        for line in f:
            name = line.split(",")[0].split(":")[1].strip()
            # 只保留name重点中文
            name = ''.join([c for c in name if '\u4e00' <= c <= '\u9fff'])
            sign_in_list.append(name)

    present_list = []
    absent_list = []

    for name in class_list:
        if name in sign_in_list:
            present_list.append(name)
        else:
            absent_list.append(name)

    # 生成存在人员名单文件
    with open(r"C:\12\2\data\present_list.txt", "w") as f:
        for name in present_list:
            f.write(name + "\n")

    # 生成不存在人员名单文件
    with open(r"C:\12\2\data\absent_list.txt", "w") as f:
        for name in absent_list:
            f.write(name + "\n")


if __name__ == "__main__":
    generate_lists()

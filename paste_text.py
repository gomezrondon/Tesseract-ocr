import pyperclip



if __name__ == "__main__":
    paste = pyperclip.paste()
    with open("out-text/paste.txt","wt") as f:
        f.write(paste)
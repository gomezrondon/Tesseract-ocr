from PIL import ImageGrab  #pip install --upgrade Pillow
im = ImageGrab.grabclipboard()
try:
    im.save('image/paste.png', "PNG")
except AttributeError:
    print("Couldn't save image {}".format('image/paste.png'))

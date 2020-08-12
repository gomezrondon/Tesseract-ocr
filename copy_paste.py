from PIL import ImageGrab



if __name__ == "__main__":
    img = ImageGrab.grabclipboard()

    # Save the image to disk
    img.save('image/paste.png', 'PNG')
    #img.save('paste.jpg', 'JPEG')
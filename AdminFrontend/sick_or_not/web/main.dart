import 'dart:html';

void main() {
  var text = querySelector('#output')..text = 'Ey!!! LEEL.';
  var button = querySelector('#LEEL');
  text.children.add(new ButtonElement()
    ..children.add(new HeadingElement.h4()..text = 'WEEW'));
  button.onClick.listen((event) {
    text.text = 'WOW';
  });
}

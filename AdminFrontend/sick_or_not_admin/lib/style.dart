import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/utils/hex_color.dart';

class Style {
  static Color surfaceColor = HexColor("#1C213E");
  static Color primaryColor = HexColor("4e7fdf");
  static Color secondaryColor = HexColor("8c89b3");
  static Color errorColor = HexColor("df4e4e ");
  static TextStyle title = TextStyle(color: Colors.white, fontSize: 34);
  static TextStyle buttonText = TextStyle(color: Colors.white, fontSize: 16, fontWeight: FontWeight.w500);
  static TextStyle overline = TextStyle(color: Colors.white, fontSize: 10, letterSpacing: 1.5);
  static TextStyle body = TextStyle(color: Colors.white, fontSize: 16);

  static Color getSurfaceColor(double height){
    return Color.alphaBlend(Colors.white.withOpacity(height / 100.0), surfaceColor);
  }
}
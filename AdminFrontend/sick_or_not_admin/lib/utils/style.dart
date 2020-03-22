import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/style.dart';
import 'hex_color.dart';

class FlutterStyle {
  static ThemeData getTheme() {
    var colorScheme = getColorScheme();
    var textTheme = getTextTheme();
    return ThemeData(
      textTheme: textTheme,
      colorScheme: colorScheme,
      primaryColor: colorScheme.primary,
      appBarTheme: AppBarTheme(textTheme: textTheme),
      cardTheme: CardTheme(
        margin: EdgeInsets.all(0)
      ),
      buttonTheme: ButtonThemeData(
        buttonColor: colorScheme.secondary,
        textTheme: ButtonTextTheme.primary,

        minWidth: 150,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(5),
        ),
      ),
      bottomAppBarTheme: BottomAppBarTheme(elevation: 10),
      errorColor: colorScheme.error,
      buttonColor: colorScheme.secondary,
      accentColor: colorScheme.secondaryVariant,
      scaffoldBackgroundColor: colorScheme.background,
      focusColor: colorScheme.secondaryVariant,
      selectedRowColor: colorScheme.secondaryVariant,
      floatingActionButtonTheme: FloatingActionButtonThemeData(backgroundColor: getColorScheme().primaryVariant),
      canvasColor: HexColor("FFFFFF"),
      snackBarTheme: SnackBarThemeData(
        actionTextColor: colorScheme.primaryVariant,
        contentTextStyle:
            textTheme.subhead.copyWith(color: colorScheme.surface),
      ),
      brightness: Brightness.light,
    );
  }

  static ColorScheme getColorScheme() {
    return ColorScheme.light(
      background: Colors.white,
      primary: Style.primaryColor,
      primaryVariant: Style.primaryColor,
      secondaryVariant: Style.secondaryColor,
      secondary: Style.secondaryColor,
      error: Style.errorColor,
      onBackground: HexColor("FFFFFF"),
      onPrimary: HexColor("FFFFFF"),
      onSecondary: HexColor("ffffff"),
      onError: HexColor("ffffff"),
      onSurface: HexColor("FFFFFF"),
      surface: Colors.white,
    );
  }

  static TextTheme getTextTheme() {
    return TextTheme(
      display2: TextStyle(
          fontSize: 60,
          fontWeight: FontWeight.w300,
          color: getColorScheme().onSurface),
      headline: TextStyle(
        fontSize: 24,
        fontWeight: FontWeight.w400,
      ),
      title: TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.bold,
        color: getColorScheme().onSurface,
      ),
      subhead: TextStyle(
        fontSize: 16,
        fontWeight: FontWeight.w400,
        letterSpacing: 0.1,
      ),
      subtitle: TextStyle(
        fontSize: 14,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.15,
      ),
      button: TextStyle(
        fontSize: 14,
        color: getColorScheme().onSecondary,
        letterSpacing: 1.25,
      ),
      body1: TextStyle(
        fontSize: 16,
        letterSpacing: 0.5,
        fontWeight: FontWeight.normal,
      ),
      body2: TextStyle(
        fontSize: 14,
        letterSpacing: 0.25,
        fontWeight: FontWeight.normal,
      ),
    );
  }
}
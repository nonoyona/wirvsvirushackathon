import 'package:flutter/material.dart';

class ResponsiveLayout extends StatelessWidget {
  final Widget largeScreen;
  final Widget mediumScreen;
  final Widget smallScreen;

  static const int SMALL_SCREEN_SIZE = 800;
  static const int MEDIUM_SCREEN_SIZE = 1200;

  const ResponsiveLayout(
      {Key key,
      @required this.largeScreen,
      this.mediumScreen,
      this.smallScreen})
      : super(key: key);

  static bool isSmallScreen(BuildContext context) {
    return MediaQuery.of(context).size.width <= SMALL_SCREEN_SIZE;
  }

  static bool isMediumScreen(BuildContext context) {
    return MediaQuery.of(context).size.width > SMALL_SCREEN_SIZE &&
        MediaQuery.of(context).size.width <= MEDIUM_SCREEN_SIZE;
  }

  static bool isLargeScreen(BuildContext context) {
    return MediaQuery.of(context).size.width > MEDIUM_SCREEN_SIZE;
  }

  static bool isThighterThan(int width, BuildContext context) {
    return MediaQuery.of(context).size.width < width;
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        var width = MediaQuery.of(context).size.width;
        if (width > MEDIUM_SCREEN_SIZE) {
          return largeScreen;
        } else if (width <= MEDIUM_SCREEN_SIZE && width > SMALL_SCREEN_SIZE) {
          return mediumScreen ?? largeScreen;
        } else {
          return smallScreen ?? mediumScreen ?? largeScreen;
        }
      },
    );
  }
}

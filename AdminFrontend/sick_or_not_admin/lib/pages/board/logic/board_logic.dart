import 'package:flutter/material.dart';

class BoardNotifier extends ChangeNotifier {
  Boards currentBoard = Boards.DASHBOARD;

  void changeBoard(Boards board) {
    if (currentBoard != board) {
      currentBoard = board;
      notifyListeners();
    }
  }
}

enum Boards { REGISTER_USER, DASHBOARD, UPDATE_CASE, CREATE_CASE }

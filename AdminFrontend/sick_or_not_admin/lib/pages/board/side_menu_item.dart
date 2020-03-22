import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class SideMenuItem extends StatefulWidget {
  final String title;
  final Widget leading;
  final Boards board;
  final bool dense;

  SideMenuItem(
      {this.title, this.leading, this.dense = false, @required this.board});

  @override
  _SideMenuItemState createState() => _SideMenuItemState();
}

class _SideMenuItemState extends State<SideMenuItem> {
  @override
  Widget build(BuildContext context) {
    print("changed: ${Provider.of<BoardNotifier>(context).currentBoard}");
    return Consumer<BoardNotifier>(
      builder: (context, value, child) {
        var selected =
        value.currentBoard == widget.board;
        return ListTile(
          title: Text(
            widget.title,
            style: Style.buttonText.copyWith(
                fontWeight: selected ? FontWeight.w700 : FontWeight.w500),
          ),
          leading: this.widget.leading,
          onTap: () =>
              value.changeBoard(widget.board),
          dense: widget.dense,
        );
      },
    );
  }
}

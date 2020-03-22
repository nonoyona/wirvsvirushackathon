import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/board/boards/case_modify/case_modification.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';
import 'package:sick_or_not_admin/pages/board/logic/board_logic.dart';
import 'package:sick_or_not_admin/style.dart';

class CaseListItem extends StatelessWidget {
  final String id, date, location, state;
  final double height;

  const CaseListItem({
    Key key,
    @required this.id,
    @required this.date,
    @required this.location,
    @required this.state,
    this.height = 5,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(5.0),
      child: Card(
        elevation: height,
        color: Style.getSurfaceColor(height),
        child: Container(
          height: 50,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              _ListItemItem(content: id),
              _ListItemItem(content: date),
              _ListItemItem(content: location),
              _ListItemItem(content: state),
              this.id != "ID"
                  ? Expanded(
                      child: IconButton(
                        color: Colors.white,
                        icon: Icon(Icons.edit),
                        onPressed: () {
                          CaseModification.date = date;
                          CaseModification.id = id;
                          CaseModification.location = location;
                          CaseModification.currentResult =
                              EnumToString.fromString(TestResult.values, state);
                          Provider.of<BoardNotifier>(context, listen: false)
                              .changeBoard(Boards.UPDATE_CASE);
                        },
                      ),
                    )
                  : Expanded(
                      child: IconButton(
                        color: Colors.white,
                        icon: Icon(Icons.search),
                        onPressed: () {
                          Scaffold.of(context).showSnackBar(
                            SnackBar(
                              content: Text(
                                "This functionality is not implemented yet",
                              ),
                              backgroundColor: Style.getSurfaceColor(10),
                              elevation: 10,
                            ),
                          );
                        },
                      ),
                    ),
            ],
          ),
        ),
      ),
    );
  }
}

class _ListItemItem extends StatelessWidget {
  final String content;

  const _ListItemItem({Key key, @required this.content}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: Text(
          content,
          style: Style.body,
        ),
      ),
    );
  }
}

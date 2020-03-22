import 'package:flutter/material.dart';
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
    return Card(
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
          ],
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

import 'package:flutter/material.dart';
import 'package:sick_or_not_admin/style.dart';

class CaseCounters extends StatelessWidget {
  const CaseCounters({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      mainAxisSize: MainAxisSize.max,
      children: <Widget>[
        _CaseCounter(caseType: "cases", count: 10000),
        _CaseCounter(caseType: "sick", count: 5378),
        _CaseCounter(caseType: "not", count: 1940,)
      ],
    );
  }
}

class _CaseCounter extends StatelessWidget {
  final String caseType;
  final int count;

  const _CaseCounter({Key key, @required this.caseType, @required this.count})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 20),
      width: 250,
      child: Card(
        elevation: 5,
        color: Style.getSurfaceColor(5),
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Text(
                caseType.toUpperCase(),
                style: Style.overline,
              ),
              SizedBox(
                height: 10,
              ),
              Text(count.toString(), style: Style.title),
            ],
          ),
        ),
      ),
    );
  }
}

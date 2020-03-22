import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/case_counter.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/case_list.dart';
import 'package:sick_or_not_admin/pages/board/boards/dash_board/dashboard_logic.dart';

class Dashboard extends StatelessWidget {
  const Dashboard({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Consumer<DashboardNotifier>(
      builder: (context, notifier, child) {
        return Container(
          padding: EdgeInsets.only(top: 20, right: 20, left: 20),
          child: ListView(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 40),
                child: CaseCounters(),
              ),
              SizedBox(
                height: 20,
              ),
              CaseListItem(
                id: "ID",
                date: "Date",
                location: "Location",
                state: "Test result",
              ),
              SizedBox(
                height: 10,
              ),
            ]..addAll(getCases(context, notifier)),
          ),
        );
      }
    );
  }

  List<Widget> getCases(BuildContext context, DashboardNotifier notifier) {

    var result = notifier.getResults();

    return result
        .map(
          (e) => CaseListItem(
            id: e.id,
            date: DateTime.fromMillisecondsSinceEpoch(e.date).toIso8601String(),
            location: e.location,
            state: EnumToString.parse(e.health),
          ),
        )
        .toList();
  }
}

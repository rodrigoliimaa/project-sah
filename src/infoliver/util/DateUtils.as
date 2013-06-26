package infoliver.util
{
	public class DateUtils
	{
		// Baseado em: http://www.alisonsouza.com.br/post/209
//		public static function calcularIdadeCompleta(niver:Date, hoje:Date = null):String
		public static function calcularIdadeCompleta(niver:Date, hoje:Date = null):int
		{
			if (!hoje)
			{
				hoje = new Date();
			}
			
//			var idade:String;
			var idade:int;
			
			var dias:Number;
			var meses:Number;
			var anos:Number;
			
			// Já fez aniversário
			if (hoje.getMonth() > niver.getMonth())
			{
				anos = hoje.getFullYear() - niver.getFullYear();
				
				if (hoje.getDate() < niver.getDate())
				{
					/* remove 1 mês, porque no mês corrente ainda
					não ultrapassou o dia da data de aniversário */
					meses = hoje.getMonth() - niver.getMonth() - 1;
					
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					meses = hoje.getMonth() - niver.getMonth();
					dias = hoje.getDate() - niver.getDate();
				}
			}
			else if (hoje.getMonth() < niver.getMonth())
			{
				// remove 1 ano porque ainda não fez aniversário
				anos = hoje.getFullYear() - niver.getFullYear() - 1;
				
				if (hoje.getDate() < niver.getDate())
				{
					meses = hoje.getMonth();
					
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					// adiciona 1 mês porque já passou do dia da data de aniversário
					meses = hoje.getMonth() + 1;
					dias = hoje.getDate() - niver.getDate();
				}
			}
			else if (hoje.getMonth() == niver.getMonth())
			{
				if (hoje.getDate() < niver.getDate())
				{
					// remove 1 ano porque ainda não fez aniversário
					anos = hoje.getFullYear() - niver.getFullYear() - 1;
					meses = hoje.getMonth() + 1;
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					anos = hoje.getFullYear() - niver.getFullYear();
					meses = hoje.getMonth() - niver.getMonth();
					dias = hoje.getDate() - niver.getDate();
				}
			}
//			idade = anos + "a " + meses + "m " + dias + "d ";
			idade = (anos * 12) + meses;
			return idade;
		}
	}
}